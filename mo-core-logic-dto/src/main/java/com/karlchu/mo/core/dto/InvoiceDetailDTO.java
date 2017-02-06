package com.karlchu.mo.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class InvoiceDetailDTO implements Serializable {

    private static final long serialVersionUID = -7759107015169357534L;
    private Long invoiceDetailId;
    private Integer quantity;
    private BigDecimal discount;
    private BigDecimal amount;
    private InvoiceDTO invoice;
    private ProductDTO product;


    public InvoiceDetailDTO() {
    }

    public InvoiceDetailDTO(Long invoiceDetailId, Integer quantity, BigDecimal discount, BigDecimal amount, InvoiceDTO invoice, ProductDTO product) {
        this.invoiceDetailId = invoiceDetailId;
        this.quantity = quantity;
        this.discount = discount;
        this.amount = amount;
        this.invoice = invoice;
        this.product = product;
    }

    public Long getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(Long invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public InvoiceDTO getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceDTO invoice) {
        this.invoice = invoice;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
