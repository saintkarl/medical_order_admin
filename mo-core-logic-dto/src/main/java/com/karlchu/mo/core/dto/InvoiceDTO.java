package com.karlchu.mo.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class InvoiceDTO implements Serializable {

    private static final long serialVersionUID = 2082144942426254062L;
    private Long invoiceId;
    private String invoiceNo;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private StoreDTO store;
    private UserDTO createdBy;
    private List<InvoiceDetailDTO> invoiceDetails;


    public InvoiceDTO() {
    }

    public InvoiceDTO(Long invoiceId, String invoiceNo, Timestamp createdDate, Timestamp modifiedDate, StoreDTO store, UserDTO createdBy) {
        this.invoiceId = invoiceId;
        this.invoiceNo = invoiceNo;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.store = store;
        this.createdBy = createdBy;
    }

    public InvoiceDTO(Long invoiceId, String invoiceNo, Timestamp createdDate, Timestamp modifiedDate, StoreDTO store, UserDTO createdBy, List<InvoiceDetailDTO> invoiceDetails) {
        this.invoiceId = invoiceId;
        this.invoiceNo = invoiceNo;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.store = store;
        this.createdBy = createdBy;
        this.invoiceDetails = invoiceDetails;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public StoreDTO getStore() {
        return store;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }

    public UserDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDTO createdBy) {
        this.createdBy = createdBy;
    }

    public List<InvoiceDetailDTO> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetailDTO> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
