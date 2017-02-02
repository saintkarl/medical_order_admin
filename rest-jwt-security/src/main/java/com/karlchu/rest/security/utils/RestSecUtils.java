package com.karlchu.rest.security.utils;

import com.karlchu.mo.common.util.CacheUtil;
import com.karlchu.rest.security.user.UserDetails;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Hieu Le on 6/28/2016.
 */
public class RestSecUtils {
    private static ThreadLocal<UserDetails> principal = new ThreadLocal<>();

    /**
     * Call this to get security principal for the request
     * @return The principal
     */
    public static UserDetails getPrincipal() {
        return principal.get();
    }

    public static void setPrincipal(UserDetails userDetails) {
        principal.set(userDetails);
    }

    /**
     * Check if user has a specified authority
     * @param authority
     * @return
     */
    public static boolean userHasAuthority(String authority) {
        UserDetails principal = getPrincipal();
        if (principal != null) {
            return principal.getAuthorities().contains(authority);
        }
        return false;
    }

    public static Map<String, Object> getClaims(final Long userId, final String userName, final String role) {
        return new HashMap<String, Object>(){{
            put(JwtConstants.JWT_CLAIM_USER_ID, userId);
            put(JwtConstants.JWT_CLAIM_USER_NAME, userName);
            if (role != null) {
                put(JwtConstants.JWT_CLAIM_USER_ROLE, role);
            }
        }};
    }

    public static void loginSuccess(UserDetails userDetails, List<String> authorities) {
        if (authorities != null && !authorities.isEmpty()) {
            userDetails.setAuthorities(new HashSet<>(authorities));
        }

        loginSuccess(userDetails);
    }

    public static void loginSuccess(UserDetails userDetails) {
        userDetails.setJwt(JwtUtils.getJwt(userDetails.getUsername(), getClaims(userDetails.getUserId(), userDetails.getUsername(), null)));

        CacheUtil.getInstance().putValue(JwtConstants.REST_PRINCIPAL_CACHE_PREFIX + userDetails.getUsername(), userDetails, JwtConstants.REST_PRINCIPAL_DEF_CACHE_EXPIRE_TIME_SECS);
    }

}
