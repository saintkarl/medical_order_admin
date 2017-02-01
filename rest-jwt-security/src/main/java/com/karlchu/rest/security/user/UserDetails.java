package com.karlchu.rest.security.user;

import java.io.Serializable;
import java.util.*;

/**
 * User: Hieu Le
 * Date: 21/06/16
 * Time: 4:49 PM
 */
public class UserDetails implements Serializable {
    private final Long userId;

    private final String username;

    private String password;

    private final String email;

    private boolean enabled;

    private Set<String> authorities;

    private String jwt;


    public UserDetails(Long userId, String username, String password, String email, boolean enabled, Collection<String> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        if (authorities != null) {
            this.authorities = new HashSet<>(authorities);
        }
    }

    public Collection<String> getAuthorities() {
        if (authorities == null) {
            authorities = new HashSet<>();
        }
        return authorities;
    }


    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
