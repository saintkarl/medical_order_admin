package com.karlchu.mo.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * E-Retailer System Platform - Copyright (c) by Ban Vien Co., Ltd. All rights reserved.
 * User: viennh
 * Date: 11/30/15
 * Time: 2:15 PM
 */
public class UserGroupDTO implements Serializable {
    private Long userGroupId;

    private String code;

    private String name;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    public UserGroupDTO(Long userGroupId, String name, String code, Timestamp createdDate, Timestamp modifiedDate) {
        this.userGroupId = userGroupId;
        this.name = name;
        this.code = code;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public UserGroupDTO() {
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
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
}
