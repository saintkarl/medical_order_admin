package com.karlchu.mo.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/23/16
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class RoleAclDTO implements Serializable {

    private Long roleAclId;
    private RoleDTO role;
    private PermissionDTO permission;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    public RoleAclDTO() {
    }

    public RoleAclDTO(Long roleAclId, RoleDTO role, PermissionDTO permission, Timestamp createdDate, Timestamp modifiedDate) {
        this.roleAclId = roleAclId;
        this.role = role;
        this.permission = permission;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getRoleAclId() {
        return roleAclId;
    }

    public void setRoleAclId(Long roleAclId) {
        this.roleAclId = roleAclId;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public PermissionDTO getPermission() {
        return permission;
    }

    public void setPermission(PermissionDTO permission) {
        this.permission = permission;
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
