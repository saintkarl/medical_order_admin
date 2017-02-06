package com.karlchu.mo.core.dto;

import java.io.Serializable;

public class StoreDTO implements Serializable {

    private static final long serialVersionUID = -5283516357109889607L;
    private Long storeId;
    private String code;
    private String name;
    private String address;
    private String tel;
    private String email;
    private String owner;
    private Integer status;


    public StoreDTO() {
    }

    public StoreDTO(Long storeId, String code, String name, String address, String tel, String email, String owner, Integer status) {
        this.storeId = storeId;
        this.code = code;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.owner = owner;
        this.status = status;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
