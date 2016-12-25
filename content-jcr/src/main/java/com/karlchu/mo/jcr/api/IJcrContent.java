package com.karlchu.mo.jcr.api;

import java.util.List;


public interface IJcrContent {
    /**
     *
     * @param fullpath ex: /contextPath/attachements/document.doc
     */
    public void removeFileItem(final String fullpath) throws JcrException;
    /**
     *
     * @param fullpath ex: /contextPath/attachements/document.doc
     * @return FileItem
     */
    public FileItem getFileItem(final String fullpath) throws JcrException;

    /**
     * Save file into node
     * @param path ex: images/gif
     * @param fileItem
     * @return fullpath ex: /contextPath/attachements/document.doc
     */
    public String write(final String path, final FileItem fileItem);


    /**
     * Save or update file into node
     * @param path ex: images/gif
     * @param fileItem
     * @return fullpath ex: /contextPath/attachements/document.doc
     */
    public String writeOrUpdate(final String path, final FileItem fileItem);

    public FileItem writeOrUpdateFileItem(final String path, final FileItem fileItem);
    /**
     *
     * @param str
     * @param offset
     * @param limit
     * @return Object[2] - Object[0] total nodes found, Object[1] List<FileItem> limited by offset and limit
     */
    public Object[] search(final String str, final long offset, final long limit);
    /**
     * @param path ex: images/gif
     * @param str
     * @param offset
     * @param limit
     * @return Object[2] - Object[0] total nodes found, Object[1] List<FileItem> limited by offset and limit
     */
    public Object[] search(final String path, final String str, final long offset, final long limit);

    public Object[] getAll(final String path);

    public List<String> listFiles(final String path);

    public List<FileItemObject> listAllFilesAndFolder(final String path);

    public List<FileItem> listStaticFiles(final String path);
}
