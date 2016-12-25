package com.karlchu.mo.core.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by khanhcq on 16-Oct-16.
 */
@Entity
@Table(name = "tip")
public class TipEntity {
    private Long tipId;
    private String title;
    private String description;
    private String thumbnail;
    private String content;
    private String tags;
    private String source;
    private TipCategoryEntity tipCategory;
    private UsersEntity createdBy;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    @Id
    @Column(name = "tipid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getTipId() {
        return tipId;
    }

    public void setTipId(Long tipId) {
        this.tipId = tipId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "tags")
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Basic
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @ManyToOne
    @JoinColumn(name = "createdby", referencedColumnName = "userid", nullable = false)
    public UsersEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UsersEntity createdBy) {
        this.createdBy = createdBy;
    }

    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "tipcategoryid", nullable = false)

    public TipCategoryEntity getTipCategory() {
        return tipCategory;
    }

    public void setTipCategory(TipCategoryEntity tipCategory) {
        this.tipCategory = tipCategory;
    }

    @Basic
    @Column(name = "createddate")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "modifieddate")
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipEntity tipEntity = (TipEntity) o;

        if (tipId != tipEntity.tipId) return false;
        if (title != null ? !title.equals(tipEntity.title) : tipEntity.title != null) return false;
        if (description != null ? !description.equals(tipEntity.description) : tipEntity.description != null)
            return false;
        if (thumbnail != null ? !thumbnail.equals(tipEntity.thumbnail) : tipEntity.thumbnail != null) return false;
        if (content != null ? !content.equals(tipEntity.content) : tipEntity.content != null) return false;
        if (tags != null ? !tags.equals(tipEntity.tags) : tipEntity.tags != null) return false;
        if (source != null ? !source.equals(tipEntity.source) : tipEntity.source != null) return false;
        if (createdDate != null ? !createdDate.equals(tipEntity.createdDate) : tipEntity.createdDate != null)
            return false;
        if (modifiedDate != null ? !modifiedDate.equals(tipEntity.modifiedDate) : tipEntity.modifiedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tipId ^ (tipId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        return result;
    }
}
