package com.karlchu.mo.core.dto;

import java.io.Serializable;

/**
 * Created by khanhcq on 25-Oct-16.
 */
public class TipDTO implements Serializable {
    private static final long serialVersionUID = -8802744335878693315L;
    private Long tipId;
    private String title;
    private String content;
    private String description;
    private String thumbnail;
    private String source;
    private String tags;
    private TipCategoryDTO tipCategory;
    private UserDTO createdBy;

    public TipDTO() {
    }

    public TipDTO(Long tipId, String title, String content, String description, String thumbnail, String source, String tags, TipCategoryDTO tipCategory) {
        this.tipId = tipId;
        this.title = title;
        this.content = content;
        this.description = description;
        this.thumbnail = thumbnail;
        this.source = source;
        this.tags = tags;
        this.tipCategory = tipCategory;
    }

    public UserDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDTO createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public TipCategoryDTO getTipCategory() {
        return tipCategory;
    }

    public void setTipCategory(TipCategoryDTO tipCategory) {
        this.tipCategory = tipCategory;
    }

    public Long getTipId() {
        return tipId;
    }

    public void setTipId(Long tipId) {
        this.tipId = tipId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
