package com.karlchu.rest.security.utils;

/**
 * Created by Hieu Le on 10/14/2016.
 */
public class JwtConstants {
    public static final String REST_PRINCIPAL_CACHE_PREFIX = "__rest_security_principal_";

    public static final String JWT_CLAIM_USER_NAME = "username";
    public static final String JWT_CLAIM_USER_ID = "userid";
    public static final String JWT_CLAIM_USER_ROLE = "role";

    public static final int REST_PRINCIPAL_DEF_CACHE_EXPIRE_TIME_SECS = 7200; // 120' TODO config this value
}
