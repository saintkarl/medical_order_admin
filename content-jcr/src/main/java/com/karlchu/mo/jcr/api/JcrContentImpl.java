package com.karlchu.mo.jcr.api;

import org.apache.log4j.Logger;
import org.springmodules.jcr.JcrCallback;
import org.springmodules.jcr.JcrTemplate;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.IOException;
import java.util.List;


public class JcrContentImpl implements IJcrContent {

    private final Logger logger = Logger.getLogger(getClass());

    private JcrTemplate template = null;
    private String webdavContextPath = "";

    public void removeFileItem(final String fullpath) {
        template.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws RepositoryException,IOException {
                JcrContentImplUtil.removeFileItem(session, fullpath);
                return null;
            }
        });
    }
    public FileItem getFileItem(final String fullpath) throws JcrException{
        Object ofile = template.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws RepositoryException,IOException {
                return JcrContentImplUtil.getFileItem(session, webdavContextPath, fullpath);
            }
        });
        return (FileItem)ofile;
    }

    public Object[] search(final String path, final String str1, final long offset, final long limit) {
        Object[] totalAndList = (Object[])template.execute(new JcrCallback() {
            public Object[] doInJcr(Session session) throws RepositoryException,IOException {
                return JcrContentImplUtil.search(session, webdavContextPath, path, str1, offset, limit);
            }
        });
        return totalAndList;
    }
    public Object[] search(final String str1, final long offset, final long limit) {
        return search("", str1, offset, limit);
    }
    /**
     *
     * @param path ex: images/gif
     * @param fileItem
     * @return
     */
    public String write(final String path, final FileItem fileItem) {
        Object fileName = template.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws RepositoryException, IOException {
                return JcrContentImplUtil.write(session, path, fileItem);
            }
        });
        return (String)fileName;
    }
    /**
     *
     * @param path ex: images/gif
     * @param fileItem
     * @return
     */
    public String writeOrUpdate(final String path, final FileItem fileItem) {
        Object fileName = template.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws RepositoryException, IOException {
                return JcrContentImplUtil.writeOrUpdate(session, path, fileItem);
            }
        });
        return (String)fileName;
    }

    public FileItem writeOrUpdateFileItem(final String path, final FileItem fileItem) {
        Object fileName = template.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws RepositoryException, IOException {
                return JcrContentImplUtil.writeOrUpdateFileItem(session, path, fileItem);
            }
        });
        return (FileItem)fileName;
    }

    public String write(final FileItem fileItem) {
        return write(null, fileItem);
    }

    public void setTemplate(JcrTemplate template) {
        this.template = template;
    }

    public void setWebdavContextPath(String webdavContextPath) {
        this.webdavContextPath = webdavContextPath;
    }

    public Object[] getAll(final String path) {
        Object[] totalAndList = (Object[])template.execute(new JcrCallback() {
            public Object[] doInJcr(Session session) throws RepositoryException,IOException {
                return JcrContentImplUtil.getAll(session, webdavContextPath, path);
            }
        });
        return totalAndList;
    }

    @Override
    public List<String> listFiles(final String path) {
        return (List<String>)template.execute(new JcrCallback() {
            public List<String> doInJcr(Session session) throws RepositoryException,IOException {
                return JcrContentImplUtil.listFiles(session, webdavContextPath, path);
            }
        });
    }

    @Override
    public List<FileItemObject> listAllFilesAndFolder(final String path) {
        return (List<FileItemObject>)template.execute(new JcrCallback() {
            public List<FileItemObject> doInJcr(Session session) throws RepositoryException,IOException {
                return JcrContentImplUtil.listAllFilesAndFolder(session, webdavContextPath, path);
            }
        });
    }


    @Override
    public List<FileItem> listStaticFiles(final String path) {
        return (List<FileItem>)template.execute(new JcrCallback() {
            public List<FileItem> doInJcr(Session session) throws RepositoryException,IOException {
                return JcrContentImplUtil.listStaticFiles(session, webdavContextPath, path);
            }
        });
    }
}
