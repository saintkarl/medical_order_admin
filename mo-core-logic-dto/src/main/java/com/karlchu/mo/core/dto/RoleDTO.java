package com.karlchu.mo.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/18/16
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class RoleDTO implements Serializable {

    private Long roleId;
    private String code;
    private String name;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    public RoleDTO() {
    }

    public RoleDTO(Long roleId, String code, String name, Timestamp createdDate, Timestamp modifiedDate) {
        this.roleId = roleId;
        this.code = code;
        this.name = name;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
