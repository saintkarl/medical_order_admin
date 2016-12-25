package com.karlchu.mo.core.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2/16/16
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "usergroupacl")
@Entity
public class UserGroupAclEntity {
    private Long userGroupAclId;

    @Column(name = "usergroupaclid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long getUserGroupAclId() {
        return userGroupAclId;
    }

    public void setUserGroupAclId(Long userGroupAclId) {
        this.userGroupAclId = userGroupAclId;
    }

    private UserGroupEntity userGroup;
    @ManyToOne
    @JoinColumn(name = "usergroupid", referencedColumnName = "usergroupid", nullable = false)
    public UserGroupEntity getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroupEntity userGroup) {
        this.userGroup = userGroup;
    }

    private PermissionEntity permission;
    @ManyToOne
    @JoinColumn(name = "permissionid", referencedColumnName = "permissionid", nullable = false)
    public PermissionEntity getPermission() {
        return permission;
    }

    public void setPermission(PermissionEntity permission) {
        this.permission = permission;
    }

    private Timestamp createdDate;

    @Column(name = "createddate")
    @Basic
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    private Timestamp modifiedDate;

    @Column(name = "modifieddate")
    @Basic
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroupAclEntity that = (UserGroupAclEntity) o;

        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;
        if (userGroupAclId != null ? !userGroupAclId.equals(that.userGroupAclId) : that.userGroupAclId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userGroupAclId != null ? userGroupAclId.hashCode() : 0;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        return result;
    }
}
