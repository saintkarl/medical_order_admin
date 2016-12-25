package com.karlchu.mo.core.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by khanhcq on 16-Oct-16.
 */
@Entity
@Table(name = "book")
public class BookEntity {
    private Long bookId;
    private String code;
    private String title;
    private String description;
    private String fileType;
    private String tags;
    private String source;
    private String coverUrl;
    private String uri;
    private String alterUri;
    private BookCategoryEntity bookCategory;
    private AuthorEntity author;
    private UsersEntity CreatedBy;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    @Id
    @Column(name = "bookid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "filetype")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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

    @Basic
    @Column(name = "coverurl")
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Basic
    @Column(name = "uri")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Basic
    @Column(name = "alteruri")
    public String getAlterUri() {
        return alterUri;
    }

    public void setAlterUri(String alterUri) {
        this.alterUri = alterUri;
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

        BookEntity that = (BookEntity) o;

        if (bookId != that.bookId) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (coverUrl != null ? !coverUrl.equals(that.coverUrl) : that.coverUrl != null) return false;
        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;
        if (alterUri != null ? !alterUri.equals(that.alterUri) : that.alterUri != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (coverUrl != null ? coverUrl.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        result = 31 * result + (alterUri != null ? alterUri.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "authorid", referencedColumnName = "authorid", nullable = false)
    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }



    @ManyToOne
    @JoinColumn(name = "bookcategoryid", referencedColumnName = "bookcategoryid", nullable = false)
    public BookCategoryEntity getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategoryEntity bookCategory) {
        this.bookCategory = bookCategory;
    }

    @ManyToOne
    @JoinColumn(name = "createdby", referencedColumnName = "userid", nullable = false)
    public UsersEntity getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(UsersEntity createdBy) {
        CreatedBy = createdBy;
    }
}
