package com.karlchu.mo.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: ANHTAI
 * Date: 2/23/16
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserGroupAclDTO implements Serializable {

    private Long userGroupAclId;
    private UserGroupDTO userGroup;
    private PermissionDTO permission;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    public UserGroupAclDTO() {
    }

    public UserGroupAclDTO(Long userGroupAclId, UserGroupDTO userGroup, PermissionDTO permission, Timestamp createdDate, Timestamp modifiedDate) {
        this.userGroupAclId = userGroupAclId;
        this.userGroup = userGroup;
        this.permission = permission;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getUserGroupAclId() {
        return userGroupAclId;
    }

    public void setUserGroupAclId(Long userGroupAclId) {
        this.userGroupAclId = userGroupAclId;
    }

    public UserGroupDTO getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroupDTO userGroup) {
        this.userGroup = userGroup;
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
