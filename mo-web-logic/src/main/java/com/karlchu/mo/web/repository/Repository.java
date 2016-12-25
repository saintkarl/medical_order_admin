package com.karlchu.mo.web.repository;

import com.karlchu.mo.jcr.api.IJcrContent;
import com.karlchu.mo.common.Constants;
import com.karlchu.mo.jcr.api.FileItem;
import com.karlchu.mo.jcr.api.FileItemObject;
import com.karlchu.mo.web.security.util.SecurityUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

public class Repository {

    private IJcrContent jcrContent;

    public IJcrContent getJcrContent() {
        return jcrContent;
    }

    public void setJcrContent(IJcrContent jcrContent) {
        this.jcrContent = jcrContent;
    }

    /**
     * @param folder ex: portalcode
     * @return Map ex : [upload_file : /contextPath/attachements/document.doc]
     */

    public Map<String, String> writeOrUpdate(HttpServletRequest request, String folder){
        Map<String, String> res = new HashMap<>();
        String path = createPathJCR(folder);
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> map = mRequest.getFileMap();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry item = (Map.Entry) iterator.next();
            MultipartFile fileUpload = (MultipartFile) item.getValue();
            if (fileUpload!= null && fileUpload.getSize() > 0){
                res.put((String)item.getKey(), (String)jcrContent.writeOrUpdate(path, multipartFile2FileItem(fileUpload)));
            }
        }
        return res;
    }

    public Map<String, List<String>> writeOrUpdateSupportMultiple(HttpServletRequest request, String folder){
        Map<String, List<String>> res = new HashMap<>();
        String path = createPathJCR(folder);
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        MultiValueMap<String, MultipartFile> map = mRequest.getMultiFileMap();
        Set keys = map.keySet();
        for(Object key : keys){
            List<MultipartFile> files = map.get(key);
            for(MultipartFile file : files){
                if(res.get(key.toString()) == null){
                    res.put(key.toString(), new ArrayList<String>());
                }
                res.get(key.toString()).add(jcrContent.writeOrUpdate(path, multipartFile2FileItem(file)));
            }
        }
        return res;
    }

    public Map<String, List<String>> writeOrUpdateSupportMultiple(HttpServletRequest request, String folder, String fileName){
        Map<String, List<String>> res = new HashMap<>();
        String path = createPathJCR(folder);
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        MultiValueMap<String, MultipartFile> map = mRequest.getMultiFileMap();
        Set keys = map.keySet();
        for(Object key : keys){
            List<MultipartFile> files = map.get(key);
            for(MultipartFile file : files){
                if(res.get(key.toString()) == null){
                    res.put(key.toString(), new ArrayList<String>());
                }
                res.get(key.toString()).add(jcrContent.writeOrUpdate(path, multipartFile2FileItem(file, fileName)));
            }
        }
        return res;
    }

    private String createPathJCR(String folder) {
        if(StringUtils.isNotBlank(folder)){
            return "/content/" + folder + "/";
        }else{
            return "/content/anonymous/";
        }
    }

    private String createPathImageJCR(String folder) {
        if(StringUtils.isNotBlank(folder)){
            return "/" + folder + "/";
        }else{
            return "/anonymous/";
        }
    }


    /**
     *
     * @param name ex: upload_file
     * @param path ex: /contextPath/attachements/
     * @return String
     */
    public String writeOrUpdate(String name, String path, HttpServletRequest request){
        String res = null;
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> map = mRequest.getFileMap();
        if (map.get(name)!= null){
            MultipartFile fileUpload = (MultipartFile) map.get(name);
            if (fileUpload!= null && fileUpload.getSize() > 0){
                res = jcrContent.writeOrUpdate(path, multipartFile2FileItem(fileUpload));
            }
        }
        return res;
    }

    public void writeMultiplePartFile(String path, MultipartFile multipartFile){
        jcrContent.writeOrUpdate(path, multipartFile2FileItem(multipartFile));
    }

    public Map<String, String> writeOrUpdateImage(HttpServletRequest request, String folder){
        Map<String, String> res = new HashMap<>();
        String path = createPathImageJCR(folder);
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> map = mRequest.getFileMap();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry item = (Map.Entry) iterator.next();
            MultipartFile fileUpload = (MultipartFile) item.getValue();
            if (fileUpload!= null && fileUpload.getSize() > 0){
                res.put((String)item.getKey(), (String)jcrContent.writeOrUpdate(path, multipartFile2FileItem(fileUpload)));
            }
        }
        return res;
    }

    public String writeOrUpdate(MultipartFile fileUpload, String path){
        if (StringUtils.isNotBlank(path)){
            path = createPathJCR(path);
        }
        String res = null;
        if (fileUpload != null){
            if (fileUpload!= null && fileUpload.getSize() > 0){
                res = jcrContent.writeOrUpdate(path, multipartFile2FileItem(fileUpload));
            }
        }
        return res;
    }

    /**
     *
     * @return String
     */
    public String writeOrUpdate(File file, String path) throws IOException {
        MultipartFile multipartFile = new CommonsMultipartFile(convertFromFile2FileItem(file));
        return writeOrUpdate(multipartFile, path);
    }


    public org.apache.commons.fileupload.FileItem convertFromFile2FileItem(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file.getPath());

        int availableBytes = inputStream.available();

        // Write the inputStream to a FileItem
        File outFile = new File("\\tmp\\newfile.xml"); // This is your tmp file, the code stores the file here in order to avoid storing it in memory
        org.apache.commons.fileupload.FileItem fileItem = new DiskFileItem("fileUpload", "plain/text", false, file.getName(), availableBytes, outFile); // You link FileItem to the tmp outFile
        OutputStream outputStream = fileItem.getOutputStream(); // Last step is to get FileItem's output stream, and write your inputStream in it. This is the way to write to your FileItem.

        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }

        // Don't forget to release all the resources when you're done with them, or you may encounter memory/resource leaks.
        inputStream.close();
        outputStream.flush(); // This actually causes the bytes to be written.
        outputStream.close();
        return  fileItem;
    }

    /**
     *
     * @param fullPath ex: /contextPath/attachements/document.doc
     */
    public void removeFileItem(String fullPath){
        jcrContent.removeFileItem(fullPath);
    }

    /**
     *
     * @param fullPath ex: /contextPath/attachements/document.doc
     * @return FileItem
     */
    public FileItem getFileItem(String fullPath){
        return jcrContent.getFileItem(fullPath);
    }

    /**
     *
     * @param name ex: upload_file
     * @return String
     */
    public String getFileType(String name, HttpServletRequest request){
        String res = null;
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> map = mRequest.getFileMap();
        if (map.get(name)!= null){
            MultipartFile fileUpload = (MultipartFile) map.get(name);
            if (fileUpload!= null && fileUpload.getSize() > 0){
                FileItem fileItem = multipartFile2FileItem(fileUpload);
                res = fileItem.getMimeType();
            }
        }
        return res;
    }

    public static FileItem multipartFile2FileItem(MultipartFile mFileItem){
        FileItem fileItem = new FileItem();
        try {
            fileItem.setData(mFileItem.getBytes());
            fileItem.setOriginalFilename(mFileItem.getOriginalFilename());
            fileItem.setMimeType(mFileItem.getContentType());
            return fileItem;
        } catch (IOException e) {

        }
        return null;
    }

    public static FileItem multipartFile2FileItem(MultipartFile mFileItem, String fileName){
        FileItem fileItem = new FileItem();
        try {
            fileItem.setOriginalFilename(fileName);
            fileItem.setData(mFileItem.getBytes());
            fileItem.setMimeType(mFileItem.getContentType());
            return fileItem;
        } catch (IOException e) {

        }
        return null;
    }

    public List<FileItemObject> listAllFilesAndFolder(String path){
        return jcrContent.listAllFilesAndFolder(path);
    }

    public String getJCRPathForTheme(String themeDirectionName){
        return new StringBuffer(Constants.JCR_SEPARATOR + SecurityUtils.getPortalCode())
                .append(Constants.JCR_SEPARATOR).append("theme").append(Constants.JCR_SEPARATOR)
                 .append(themeDirectionName).toString();
    }
}
