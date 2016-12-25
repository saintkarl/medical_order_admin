package com.karlchu.mo.jcr.api;

import java.sql.Timestamp;
import java.util.List;

public class FileItemObject {
    private String path;

    private String name;

    private FileItemObject parent;

    private Boolean directory;

    private Integer nodeLevel;

    private String type;

    private String size;

    private Timestamp modifiedDate;

    private String content;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    private List<FileItemObject> children;

    public List<FileItemObject> getChildren() {
        return children;
    }

    public void setChildren(List<FileItemObject> children) {
        this.children = children;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileItemObject getParent() {
        return parent;
    }

    public void setParent(FileItemObject parent) {
        this.parent = parent;
    }

    public Boolean getDirectory() {
        return directory;
    }

    public void setDirectory(Boolean directory) {
        this.directory = directory;
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FileItemObject(){

    }

    public FileItemObject(String path, String name, FileItemObject parent, Boolean directory, Integer nodeLevel, String type) {
        this.path = path;
        this.name = name;
        this.parent = parent;
        this.directory = directory;
        this.nodeLevel = nodeLevel;
        if (directory){
            this.type = "folder";
        }else{
            this.type = getFileTypeByName(name);
        }

    }

    public FileItemObject(String path, String name, Long size, Long modifiedDate){
        this.path = path;
        this.name = name;
        this.size = getSizeFile(size);
        if(modifiedDate != null && modifiedDate > 0){
            this.modifiedDate = new Timestamp(modifiedDate);
        } else {
            this.modifiedDate = null;
        }
        this.directory = false;
        this.type = getFileTypeByName(name);
    }

    public FileItemObject(String path, String name, FileItemObject parent, Boolean directory, Integer nodeLevel, String type, Long size, Timestamp modifiedDate) {
        this.path = path;
        this.name = name;
        this.parent = parent;
        this.directory = directory;
        this.nodeLevel = nodeLevel;
        if (directory){
            this.type = "folder";
        }else{
            this.type = getFileTypeByName(name);
        }
        this.size = getSizeFile(size);
        this.modifiedDate = modifiedDate;
    }

    private String getSizeFile(Long size){
        String strSize = "";
        if(size != null && size > 0){
            if(size > 0 && size < 1024){
                strSize = size.toString() + " byte";
            } else if (size > 1024 && size <= 1048576){
                strSize = String.valueOf(Math.floor(size*100/1024)/100) + " Kb";
            } else if (size > 1048576 && size <= 1073741824){
                strSize = String.valueOf(Math.floor(size*100/1048576)/100) + " MB";
            } else if (size > 1073741824 && size <= 1099511627776l) {
                strSize = String.valueOf(Math.floor(size*100/1073741824)/100) + " GB";
            } else if (size > 1099511627776l){
                strSize = String.valueOf(Math.floor(size*100/1099511627776l)/100) + " TB";
            }
        } else {
            strSize = "";
        }
        return strSize;
    }

    private String getFileTypeByName(String name){
        String fileType = name;
        if(name.endsWith(".jpeg") || name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".gif")){
            fileType = "image";
        } else if (name.endsWith(".mp3") || name.endsWith(".wav")){
            fileType = "audio";
        } else if (name.endsWith(".js")){
            fileType = "js";
        } else if (name.endsWith(".css")){
            fileType = "css";
        } else if(name.endsWith(".html")){
            fileType = "html";
        } else if (name.endsWith(".txt") || name.endsWith(".md") || name.endsWith(".ini")){
            fileType = "text";
        } else if (name.endsWith(".zip") || name.endsWith(".rar")) {
            fileType = "archive";
        } else {
            fileType = "file";
        }
        return fileType;
    }
}
