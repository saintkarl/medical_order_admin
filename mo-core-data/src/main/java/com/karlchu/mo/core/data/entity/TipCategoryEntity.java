package com.karlchu.mo.core.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by khanhcq on 16-Oct-16.
 */
@Entity
@Table(name = "tipcategory")
public class TipCategoryEntity {
    private Long tipCategoryId;
    private String code;
    private String name;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private List<TipEntity> tips;

    @Id
    @Column(name = "tipcategoryid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getTipCategoryId() {
        return tipCategoryId;
    }

    public void setTipCategoryId(Long tipCategoryId) {
        this.tipCategoryId = tipCategoryId;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        TipCategoryEntity that = (TipCategoryEntity) o;

        if (tipCategoryId != that.tipCategoryId) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tipCategoryId ^ (tipCategoryId >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tipCategory")

    public List<TipEntity> getTips() {
        return tips;
    }

    public void setTips(List<TipEntity> tips) {
        this.tips = tips;
    }
}
