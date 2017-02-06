package com.karlchu.mo.core.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by khanhcq on 06-Feb-17.
 */
@Entity
@Table(name = "invoice")
public class InvoiceEntity {
    private Long invoiceId;
    private String invoiceNo;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private StoreEntity store;
    private UsersEntity createdBy;
    private List<InvoiceDetailEntity> invoiceDetails;

    @Id
    @Column(name = "invoiceid")
    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Basic
    @Column(name = "invoiceno")
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
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

        InvoiceEntity that = (InvoiceEntity) o;

        if (invoiceId != null ? !invoiceId.equals(that.invoiceId) : that.invoiceId != null) return false;
        if (invoiceNo != null ? !invoiceNo.equals(that.invoiceNo) : that.invoiceNo != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = invoiceId != null ? invoiceId.hashCode() : 0;
        result = 31 * result + (invoiceNo != null ? invoiceNo.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        return result;
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
    @JoinColumn(name = "storeid", referencedColumnName = "storeid", nullable = false)
    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    @OneToMany(mappedBy = "invoice")
    public List<InvoiceDetailEntity> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetailEntity> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
