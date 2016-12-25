package com.karlchu.mo.core.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * E-Retailer System Platform - Copyright (c) by Ban Vien Co., Ltd. All rights reserved.
 * User: viennh
 * Date: 11/30/15
 * Time: 2:30 PM
 */
public class UserDemographicDTO implements Serializable {
    private Long userDemographicId;

    private String sex;

    private Date birthday;

    private String placeOfBirth;

    private String avatar;

    private String profileUrl;

    private UserDTO user;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    public Long getUserDemographicId() {
        return userDemographicId;
    }

    public void setUserDemographicId(Long userDemographicId) {
        this.userDemographicId = userDemographicId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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
