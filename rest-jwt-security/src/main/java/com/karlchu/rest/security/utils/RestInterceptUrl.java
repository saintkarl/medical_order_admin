package com.karlchu.rest.security.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hieu Le on 6/28/2016.
 */
public class RestInterceptUrl implements Serializable {
    private String pattern;
    private List<String> roles;

    public RestInterceptUrl(String pattern, List<String> roles) {
        this.pattern = pattern;
        this.roles = roles;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
