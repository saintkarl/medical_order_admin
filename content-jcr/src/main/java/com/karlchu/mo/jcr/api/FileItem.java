package com.karlchu.mo.jcr.api;

import java.io.InputStream;
import java.io.Serializable;


public class FileItem implements Serializable{
    private byte[] data;

    private InputStream inputStream;

    private String originalFilename;

    private long lastModified;//millis

    private String path;

    private String hrefPath;

    private String mimeType;

    private Long size;

    // this property only for scaled images
    private String version;

    public FileItem(){

    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHrefPath() {
        return hrefPath;
    }

    public void setHrefPath(String hrefPath) {
        this.hrefPath = hrefPath;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
