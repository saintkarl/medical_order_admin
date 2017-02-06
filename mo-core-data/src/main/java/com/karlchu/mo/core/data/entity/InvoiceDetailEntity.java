package com.karlchu.mo.core.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by khanhcq on 06-Feb-17.
 */
@Entity
@Table(name = "invoicedetail")
public class InvoiceDetailEntity {
    private Long invoiceDetailId;
    private Integer quantity;
    private BigDecimal discount;
    private BigDecimal amount;
    private InvoiceEntity invoice;
    private ProductEntity product;

    @Id
    @Column(name = "invoicedetailid")
    public Long getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(Long invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "discount")
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceDetailEntity that = (InvoiceDetailEntity) o;

        if (invoiceDetailId != null ? !invoiceDetailId.equals(that.invoiceDetailId) : that.invoiceDetailId != null)
            return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = invoiceDetailId != null ? invoiceDetailId.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "invoiceid", referencedColumnName = "invoiceid")
    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntity invoice) {
        this.invoice = invoice;
    }

    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "productid", nullable = false)
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
