package com.karlchu.mo.web.util;


import com.karlchu.mo.jcr.api.FileItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class BeanUtil {
    public static FileItem toJcrFileItem(
            FileItem fileItem) {
        FileItem jcrFileItem = new FileItem();
        jcrFileItem.setData(fileItem.getData());
        jcrFileItem.setHrefPath(fileItem.getHrefPath());
        jcrFileItem.setLastModified(fileItem.getLastModified());
        jcrFileItem.setOriginalFilename(fileItem.getOriginalFilename());
        jcrFileItem.setPath(fileItem.getPath());
        jcrFileItem.setMimeType(fileItem.getMimeType());
        return jcrFileItem;
    }

    public static FileItem toFileItem(
            FileItem jcrFileItem) {
        FileItem fileItem = new FileItem();
        fileItem.setData(jcrFileItem.getData());
        fileItem.setHrefPath(jcrFileItem.getHrefPath());
        fileItem.setLastModified(jcrFileItem.getLastModified());
        fileItem.setOriginalFilename(jcrFileItem.getOriginalFilename());
        fileItem.setPath(jcrFileItem.getPath());
        fileItem.setMimeType(jcrFileItem.getMimeType());
        return fileItem;
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
}
