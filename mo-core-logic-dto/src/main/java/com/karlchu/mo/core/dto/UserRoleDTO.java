package com.karlchu.mo.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * E-Retailer System Platform - Copyright (c) by Ban Vien Co., Ltd. All rights reserved.
 * User: viennh
 * Date: 11/30/15
 * Time: 2:12 PM
 */
public class UserRoleDTO implements Serializable {
    private Long userRoleACLId;
    private UserDTO user;
    private RoleDTO role;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    public Long getUserRoleACLId() {
        return userRoleACLId;
    }

    public void setUserRoleACLId(Long userRoleACLId) {
        this.userRoleACLId = userRoleACLId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
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
