package com.karlchu.mo.jcr.api;

import com.karlchu.mo.jcr.util.JcrUtils;
import com.karlchu.mo.jcr.util.Utils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.jackrabbit.JcrConstants;
import org.apache.log4j.Logger;

import javax.activation.MimetypesFileTypeMap;
import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.Row;
import javax.jcr.query.RowIterator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class JcrContentImplUtil {
    public static final String MIME_TYPE_VERSION_SEP = "#";
    private final static Logger logger = Logger.getLogger(JcrContentImplUtil.class);

    public static void removeFileItem(Session session, final String fullpath) throws JcrException{
        try {
            Node file = (Node) session.getItem(fullpath);
            file.remove();
            session.save();
        } catch (Exception e) {
            throw new JcrException(e);
        }
    }
    public static FileItem getFileItem(Session session, String webdavContextPath, final String fullpath) throws JcrException{
        try {
            String wspName = session.getWorkspace().getName();
            Node file = (Node) session.getItem(fullpath);
            FileItem fileItem = nodeFile2FileItem(webdavContextPath, wspName, file);

            return fileItem;
        } catch (Exception e) {
            throw new JcrException(e);
        }
    }

    public static Object[] search(Session session, String webdavContextPath, final String path
            , final String str1, final long offset, final long limit) throws JcrException {
        final long to = offset + limit;
        long total = 0;
        String wspName = session.getWorkspace().getName();
        String str = str1;
        if (str == null) {
            str = "";
        }
        RowIterator rows = null;
        if (str != null && str.length() > 0) {
            str = str.replaceAll("'", "''");
            //String stmt = "//element(*, nt:file)[jcr:contains(jcr:content, '" + str + "')]/rep:excerpt(.)";
            String stmt = "//element(*, nt:file)";
            if(StringUtils.isNotBlank(path)) {
                stmt = "/jcr:root/" + path + stmt;
            }

            try {
                Query query = session.getWorkspace().getQueryManager().createQuery(stmt, Query.XPATH);

                rows = query.execute().getRows();
            } catch (Exception e) {
                throw new JcrException(e);
            }
            //total = rows.getSize();
            //logger.info("search jcr totalResults -----------" + rows.getSize());
        }

        if(rows != null) {
            try {
                rows.skip(offset);
                total = offset;
            } catch (Exception e) {
            }
        }
        List fileItems = new ArrayList();
        if(rows != null){
            while (rows.hasNext()) {
                Row r = rows.nextRow();
                if(rows.getPosition() <= to){
                    try {
                        Node file = (Node) session.getItem(r.getValue("jcr:path").getString());
                        FileItem fileItem = nodeFile2FileItem(webdavContextPath, wspName, file);
                        fileItems.add(fileItem);

                    } catch (Exception e) {
                        throw new JcrException(e);
                    }
                }
                total ++;
            }
        }

        return new Object[] {total, fileItems};

    }

    public static Object[] getAll(Session session, String webdavContextPath, final String path){
        String wspName = session.getWorkspace().getName();

        RowIterator rows = null;

        String stmt = "//element(*, nt:file)";
        if(StringUtils.isNotBlank(path)) {
            stmt = "/jcr:root/" + path + stmt;
        }
        try {
            Query query = session.getWorkspace().getQueryManager().createQuery(stmt, Query.XPATH);
            rows = query.execute().getRows();
        } catch (Exception e) {
            throw new JcrException(e);
        }

        int total = 0;
        List fileItems = new ArrayList();
        if(rows != null){
            while (rows.hasNext()) {
                Row r = rows.nextRow();
                try {
                    Node file = (Node) session.getItem(r.getValue("jcr:path").getString());
                    FileItem fileItem = nodeFile2FileItem(webdavContextPath, wspName, file);
                    fileItems.add(fileItem);
                    total++;
                } catch (Exception e) {
                    throw new JcrException(e);
                }
            }
        }
        return new Object[] {total, fileItems};
    }

    public static Object[] search(Session session, String webdavContextPath
            , final String str1, final long offset, final long limit) throws JcrException {
        return search(session, webdavContextPath, "", str1, offset, limit);
    }
    /**
     *
     * @param path ex: images/gif
     * @param fileItem
     * @return
     */
    public static String write(Session session, final String path, final FileItem fileItem) throws JcrException {
        if ((fileItem.getData() == null || fileItem.getData().length == 0) && fileItem.getInputStream() == null) {
            return null;
        }
        try {
            Node rt = session.getRootNode();
            Node contextNode = rt;
            Node prNode = null;//parent node

            if(path == null) {
                prNode = contextNode;
            }else {
                String[] strs = path.split("/");
                prNode = contextNode;
                Node child = null;
                for(String str : strs) {
                    child = null;
                    if(StringUtils.isNotBlank(str)) {
                        if(prNode.hasNode(str)) {
                            child = prNode.getNode(str);
                        }else {
                            child =  prNode.addNode(str);
                        }
                        if(child != null) {
                            prNode = child;
                        }
                    }

                }
            }
            String fileNodeName = fileItem.getOriginalFilename();
            fileNodeName = Utils.normalize(fileNodeName);
            fileNodeName = fileNodeName.replaceAll(" ", "");
            String oriFileNodeName = fileNodeName;
            while(prNode.hasNode(fileNodeName)) {
                fileNodeName = oriFileNodeName;
                String now = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
                int atDot =  fileNodeName.lastIndexOf(".");
                if(atDot >= 0) {
                    fileNodeName = fileNodeName.substring(0, atDot) + "-" + now
                            + fileNodeName.substring(atDot);
                }else {
                    fileNodeName += "_" + now;
                }
            }

            Node fileNode = prNode.addNode(fileNodeName, "nt:file");
            Node resNode = fileNode.addNode("jcr:content", "nt:resource");

            String mimeType = getMimeType(fileItem.getOriginalFilename());
            if (StringUtils.isNotBlank(fileItem.getVersion())) {
                mimeType += MIME_TYPE_VERSION_SEP + fileItem.getVersion();
            }
            resNode.setProperty(JcrConstants.JCR_MIMETYPE, mimeType);
            resNode.setProperty(JcrConstants.JCR_ENCODING, "UTF-8");
            resNode.setProperty(JcrConstants.JCR_DATA, fileItem.getData() != null && fileItem.getData().length > 0? new ByteArrayInputStream(fileItem.getData()) : fileItem.getInputStream());
            Calendar lastModified = Calendar.getInstance();
            lastModified.setTimeInMillis(fileItem.getLastModified());
            resNode.setProperty(JcrConstants.JCR_LASTMODIFIED, lastModified);

            session.save();

            return fileNode.getPath();
        } catch (Exception e) {
            throw new JcrException(e);
        }
    }

    public static String writeOrUpdate(Session session, final String path, final FileItem fileItem) throws JcrException {
        if ((fileItem.getData() == null || fileItem.getData().length == 0) && fileItem.getInputStream() == null) {
            return null;
        }
        try {
            Node rt = session.getRootNode();
            Node contextNode = rt;
            Node prNode = null;//parent node

            if(path == null) {
                prNode = contextNode;
            }else {
                String[] strs = path.split("/");
                prNode = contextNode;
                Node child = null;
                for(String str : strs) {
                    child = null;
                    if(StringUtils.isNotBlank(str)) {
                        if(prNode.hasNode(str)) {
                            child = prNode.getNode(str);
                        }else {
                            child =  prNode.addNode(str);
                        }
                        if(child != null) {
                            prNode = child;
                        }
                    }

                }
            }
            String fileNodeName = fileItem.getOriginalFilename();
            fileNodeName = Utils.normalize(fileNodeName);
            fileNodeName = fileNodeName.replaceAll(" ", "");

            Node fileNode = null;
            Node resNode = null;

            fileNode = prNode;
            while (fileNode.hasNode(fileNodeName)){
                fileNode = fileNode.getNode(fileNodeName);
            }
            if(fileNode.getPrimaryNodeType().isNodeType("nt:file")) {
                resNode = fileNode.getNode("jcr:content");
            }else{
                fileNode = null;
            }

            if(fileNode == null){
                fileNode = prNode.addNode(fileNodeName, "nt:file");
                resNode = fileNode.addNode("jcr:content", "nt:resource");
            }
            if(resNode == null) {
                resNode = fileNode.addNode("jcr:content", "nt:resource");
            }

            String mimeType = getMimeType(fileItem.getOriginalFilename());
            if (StringUtils.isNotBlank(fileItem.getVersion())) {
                mimeType += MIME_TYPE_VERSION_SEP + fileItem.getVersion();
            }
            resNode.setProperty(JcrConstants.JCR_MIMETYPE, mimeType);
            resNode.setProperty(JcrConstants.JCR_ENCODING, "UTF-8");
            resNode.setProperty(JcrConstants.JCR_DATA, fileItem.getData() != null && fileItem.getData().length > 0? new ByteArrayInputStream(fileItem.getData()) : fileItem.getInputStream());
            Calendar lastModified = Calendar.getInstance();
            lastModified.setTimeInMillis(fileItem.getLastModified());
            resNode.setProperty(JcrConstants.JCR_LASTMODIFIED, lastModified);

            session.save();

            return fileNode.getPath();
        } catch (Exception e) {
            throw new JcrException(e);
        }
    }

    public static FileItem writeOrUpdateFileItem(Session session, final String path, final FileItem fileItem) throws JcrException {
        if ((fileItem.getData() == null || fileItem.getData().length == 0) && fileItem.getInputStream() == null) {
            return null;
        }
        try {
            FileItem returnFileItem = new FileItem();
            Node rt = session.getRootNode();
            Node contextNode = rt;
            Node prNode = null;//parent node

            if(path == null) {
                prNode = contextNode;
            }else {
                String[] strs = path.split("/");
                prNode = contextNode;
                Node child = null;
                for(String str : strs) {
                    child = null;
                    if(StringUtils.isNotBlank(str)) {
                        if(prNode.hasNode(str)) {
                            child = prNode.getNode(str);
                        }else {
                            child =  prNode.addNode(str);
                        }
                        if(child != null) {
                            prNode = child;
                        }
                    }

                }
            }
            String fileNodeName = fileItem.getOriginalFilename();
            fileNodeName = Utils.normalize(fileNodeName);
            fileNodeName = fileNodeName.replaceAll(" ", "");

            Node fileNode = null;
            Node resNode = null;

            fileNode = prNode;
            while (fileNode.hasNode(fileNodeName)){
                fileNode = fileNode.getNode(fileNodeName);
            }
            if(fileNode.getPrimaryNodeType().isNodeType("nt:file")) {
                resNode = fileNode.getNode("jcr:content");
            }else{
                fileNode = null;
            }

            if(fileNode == null){
                fileNode = prNode.addNode(fileNodeName, "nt:file");
                resNode = fileNode.addNode("jcr:content", "nt:resource");
            }
            if(resNode == null) {
                resNode = fileNode.addNode("jcr:content", "nt:resource");
            }
            String mimeType = getMimeType(fileItem.getOriginalFilename());
            if (StringUtils.isNotBlank(fileItem.getVersion())) {
                mimeType += MIME_TYPE_VERSION_SEP + fileItem.getVersion();
            }
            resNode.setProperty(JcrConstants.JCR_MIMETYPE, mimeType);
            resNode.setProperty(JcrConstants.JCR_ENCODING, "UTF-8");
            resNode.setProperty(JcrConstants.JCR_DATA, fileItem.getData() != null && fileItem.getData().length > 0? new ByteArrayInputStream(fileItem.getData()) : fileItem.getInputStream());
            Calendar lastModified = Calendar.getInstance();
            lastModified.setTimeInMillis(fileItem.getLastModified());
            resNode.setProperty(JcrConstants.JCR_LASTMODIFIED, lastModified);

            session.save();

            returnFileItem.setPath(fileNode.getPath());
            returnFileItem.setOriginalFilename(fileNode.getName());
            returnFileItem.setLastModified(fileNode.getProperty("jcr:content/jcr:lastModified").getDate().getInstance().getTimeInMillis());
            Long size = fileNode.getProperty("jcr:content/jcr:data").getLength();
            returnFileItem.setSize(size);
            return returnFileItem;
        } catch (Exception e) {
            throw new JcrException(e);
        }
    }

    private static String getMimeType(String originalFilename) {
        if(originalFilename.toLowerCase().endsWith(".htm") || originalFilename.toLowerCase().endsWith(".html")){
            return "text/html;charset=UTF-8";
        }
        MimetypesFileTypeMap mt = new MimetypesFileTypeMap();
        String mimeType = mt.getContentType(originalFilename);
        if (mimeType == null || originalFilename.endsWith(".swf")) {
            if (originalFilename.endsWith(".doc")) {
                mimeType = "application/msword";
            } else if (originalFilename.endsWith(".xls")) {
                mimeType = "application/vnd.ms-excel";
            } else if (originalFilename.endsWith(".ppt")) {
                mimeType = "application/mspowerpoint";
            } else if(originalFilename.endsWith(".swf")){
                mimeType = "application/x-shockwave-flash";
            } else {
                mimeType = "application/octet-stream";
            }
        }
        return mimeType;
    }

    public static String write(Session session, final FileItem fileItem) throws JcrException{
        return write(session, null, fileItem);

    }

    private static FileItem nodeFile2FileItem(String webdavContextPath, String wspName, Node file)
            throws PathNotFoundException, RepositoryException, ValueFormatException {
        Node resource = file.getNode(JcrConstants.JCR_CONTENT);

        FileItem fileItem = new FileItem();
        if (resource.hasProperty(JcrConstants.JCR_DATA)) {
            try {
                fileItem.setData(IOUtils.toByteArray(resource.getProperty(JcrConstants.JCR_DATA).getStream()));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            fileItem.setLastModified(resource.getProperty(JcrConstants.JCR_LASTMODIFIED).getDate().getTimeInMillis());
            parseMimeType(resource.getProperty(JcrConstants.JCR_MIMETYPE).getString(), fileItem);
        }
        fileItem.setHrefPath(JcrUtils.createHrefPathWebdav(webdavContextPath, wspName, file.getPath()));
        fileItem.setOriginalFilename(file.getName());
        fileItem.setPath(file.getPath());

        return fileItem;
    }

    private static FileItem nodeFile2FileItemNotFetchdata(String webdavContextPath, String wspName, Node file)
            throws PathNotFoundException, RepositoryException, ValueFormatException {
        Node resource = file.getNode(JcrConstants.JCR_CONTENT);

        FileItem fileItem = new FileItem();
        if (resource.hasProperty(JcrConstants.JCR_DATA)) {
            fileItem.setLastModified(resource.getProperty(JcrConstants.JCR_LASTMODIFIED).getDate().getTimeInMillis());
            parseMimeType(resource.getProperty(JcrConstants.JCR_MIMETYPE).getString(), fileItem);
        }
        fileItem.setHrefPath(JcrUtils.createHrefPathWebdav(webdavContextPath, wspName, file.getPath()));
        fileItem.setOriginalFilename(file.getName());
        fileItem.setPath(file.getPath());

        return fileItem;
    }

    public static List<String> listFiles(Session session, String webdavContextPath, String path) {
        List<String> fileItems = new ArrayList<String>();
        try {
            Node node = (Node)session.getItem(path);
            NodeIterator nodeIterator = node.getNodes();
            while(nodeIterator.hasNext()) {
                Node n = nodeIterator.nextNode();
                if(n.getPrimaryNodeType().getName().equals("nt:file")) {
                    fileItems.add(n.getPath());
                }else{
                    fileItems.addAll(getChildNodes(n));
                }
            }
        } catch (RepositoryException e) {
        }

        return fileItems;
    }

    public static List<FileItemObject> listAllFilesAndFolder(Session session, String webdavContextPath, String path) {
        List<FileItemObject> fileItems = new ArrayList<FileItemObject>();
        try {
            Node node = (Node)session.getItem(path);
            NodeIterator nodeIterator = node.getNodes();
            Integer nodeLevel = 0;
            while(nodeIterator.hasNext()) {
                Node n = nodeIterator.nextNode();
                if(n.getPrimaryNodeType().getName().equals("nt:file")) {
                    fileItems.add(getFileItemObject(n, null, nodeLevel));
                }else{
                    FileItemObject currentNode = getFileItemObject(n, null, nodeLevel);
                    fileItems.add(currentNode);
                    fileItems.addAll(getAllFileItemOrFolder(n, currentNode, nodeLevel + 1));
                }
            }

        } catch (RepositoryException e) { }

        return fileItems;
    }

    private static List<FileItemObject> getAllFileItemOrFolder(Node node, FileItemObject parent, Integer nodeLevel) throws RepositoryException {
        List<FileItemObject> fileItems = new ArrayList<FileItemObject>();
        NodeIterator nodeIterator = node.getNodes();
        while (nodeIterator.hasNext()) {
            Node n = nodeIterator.nextNode();
            if(n.getPrimaryNodeType().getName().equals("nt:file")) {
                fileItems.add(getFileItemObject(n, parent, nodeLevel));
            }else{
                FileItemObject currentNode = getFileItemObject(n, parent, nodeLevel);
                fileItems.add(currentNode);
                fileItems.addAll(getAllFileItemOrFolder(n, currentNode, nodeLevel + 1));
            }
        }
        return fileItems;

    }

    private static FileItemObject getFileItemObject(Node node, FileItemObject parentNode, Integer nodeLevel) throws RepositoryException{
        String path = node.getPath();
        String name = node.getName();
        boolean isDirectory = true;
        String type = "";
        Long size = null;
        Timestamp modifiedDate = null;
        if(node.getPrimaryNodeType().getName().equals("nt:file")){
            isDirectory = false;
            size = node.getProperty("jcr:content/jcr:data").getLength();
            modifiedDate = new Timestamp(node.getProperty("jcr:content/jcr:lastModified").getDate().getInstance().getTimeInMillis());
        } else {
            try{
                modifiedDate = new Timestamp(node.getProperty("jcr:content/jcr:lastModified").getDate().getInstance().getTimeInMillis());
            } catch (Exception e){
                modifiedDate = null;
            }
        }
        return new FileItemObject(path, name, parentNode, isDirectory, nodeLevel, type, size, modifiedDate);
    }

    public static List<FileItem> listStaticFiles(Session session, String webdavContextPath, String path) {
        List<FileItem> fileItems = new ArrayList<FileItem>();
        String wspName = session.getWorkspace().getName();
        try {
            Node node = (Node)session.getItem(path);
            NodeIterator nodeIterator = node.getNodes();
            while(nodeIterator.hasNext()) {
                Node n = nodeIterator.nextNode();
                if(n.getPrimaryNodeType().getName().equals("nt:file")) {
                    if(n.getName().indexOf(".css") >= 0 || n.getName().indexOf(".js") >= 0 ){
                        fileItems.add(nodeFile2FileItem(webdavContextPath, wspName, n));
                    }
                }else{
                    fileItems.addAll(getStaticChildNodes(n, webdavContextPath, wspName));
                }
            }
        } catch (RepositoryException e) {
        }

        return fileItems;
    }

    private static List<FileItem> getStaticChildNodes(Node node, String webdavContextPath, String wspName) throws RepositoryException {
        List<FileItem> res = new ArrayList<FileItem>();
        NodeIterator nodeIterator = node.getNodes();
        while (nodeIterator.hasNext()) {
            Node n = nodeIterator.nextNode();
            if(n.getPrimaryNodeType().getName().equals("nt:file")) {
                if(n.getName().indexOf(".css") >= 0 || n.getName().indexOf(".js") >= 0 ){
                    res.add(nodeFile2FileItem(webdavContextPath, wspName, n));
                }
            }else{
                res.addAll(getStaticChildNodes(n, webdavContextPath, wspName));
            }
        }
        return res;
    }

    private static List<String> getChildNodes(Node node) throws RepositoryException {
        List<String> res = new ArrayList<String>();
        NodeIterator nodeIterator = node.getNodes();
        while (nodeIterator.hasNext()) {
            Node n = nodeIterator.nextNode();
            if(n.getPrimaryNodeType().getName().equals("nt:file")) {
                res.add(n.getPath());
            }else{
                res.addAll(getChildNodes(n));
            }
        }
        return res;
    }

    private static void parseMimeType(String mimeType, FileItem fileItem) {
        if (mimeType != null) {
            int n = mimeType.indexOf(MIME_TYPE_VERSION_SEP);
            if (n > 1) {
                fileItem.setMimeType(mimeType.substring(0, n));
                fileItem.setVersion(mimeType.substring(n + 1));
            } else {
                fileItem.setMimeType(mimeType);
            }
        }
    }
}
