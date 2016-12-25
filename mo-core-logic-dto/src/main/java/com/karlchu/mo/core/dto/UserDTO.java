package com.karlchu.mo.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * E-Retailer System Platform - Copyright (c) by Ban Vien Co., Ltd. All rights reserved.
 * User: viennh
 * Date: 11/30/15
 * Time: 2:12 PM
 */
public class UserDTO implements Serializable {
    private Long userId;

    private String userName;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private UserGroupDTO userGroup;

    private UserDemographicDTO userDemographicDTO;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    private String code;

    private Integer status;

    private List<RoleDTO> roles;

    private List<PermissionDTO> permissions;



    public UserDTO() {
    }

    public UserDTO(Long userId, String userName, String password, String firstName, String lastName, String email, String phoneNumber,
                   String code, int status,  Timestamp createdDate, Timestamp modifiedDate) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public UserDTO(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserDemographicDTO getUserDemographicDTO() {
        return userDemographicDTO;
    }

    public void setUserDemographicDTO(UserDemographicDTO userDemographicDTO) {
        this.userDemographicDTO = userDemographicDTO;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserGroupDTO getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroupDTO userGroup) {
        this.userGroup = userGroup;
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

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
}
