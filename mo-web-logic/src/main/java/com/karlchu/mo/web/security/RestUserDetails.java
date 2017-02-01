package com.karlchu.mo.web.security;

import com.karlchu.rest.security.user.UserDetails;

import java.util.List;

/**
 * Created by Hieu Le on 10/14/2016.
 */
public class RestUserDetails extends UserDetails{
    private String code;
    private String fullName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long customerId;
    private String customerCode;
    private Boolean needChangePassword = false;

    public RestUserDetails(Long userId, String username, String password, String email, boolean enabled, List<String> authorities) {
        super(userId, username, password, email, enabled, authorities);
    }

    public Boolean getNeedChangePassword() {
        return needChangePassword;
    }

    public void setNeedChangePassword(Boolean needChangePassword) {
        this.needChangePassword = needChangePassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
