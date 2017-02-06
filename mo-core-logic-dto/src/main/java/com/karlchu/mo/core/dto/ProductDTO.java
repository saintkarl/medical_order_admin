package com.karlchu.mo.core.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 4188992949464196098L;
    private Long productId;
    private String code;
    private String name;


    public ProductDTO() {
    }

    public ProductDTO(Long productId, String code, String name) {
        this.productId = productId;
        this.code = code;
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
