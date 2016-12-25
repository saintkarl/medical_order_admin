package com.karlchu.mo.web.util;

import io.jsonwebtoken.*;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by Hieu Le on 6/28/2016.
 */
public class JWTUtils {
    private static final Logger logger = Logger.getLogger(JWTUtils.class);

    private static byte[] secretBytes = "ErSBVSec689".getBytes();

    public static String getJwt(Map<String, Object> propertyMap) {
        return getJwt("User", propertyMap, SignatureAlgorithm.HS256);
    }

    public static String getJwt(String subject, Map<String, Object> propertyMap) {
        return getJwt(subject, propertyMap, SignatureAlgorithm.HS256);
    }

    public static String getJwt(String subject, Map<String, Object> propertyMap, SignatureAlgorithm algorithm) {
        return Jwts.builder().setSubject(subject)
                .setIssuer("Retirement")
                .setClaims(propertyMap)
                .signWith(algorithm, secretBytes).compact();
    }

    /**
     *
     * @param jwt
     * @return claims, null if the token is invalid
     */
    public static Map<String, Object> verify(String jwt) {
        Map<String, Object> res = null;
        try {

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretBytes).parseClaimsJws(jwt);
            res = claimsJws.getBody();

        } catch (Exception e) {
            logger.warn("Token " + jwt + " is invalid");
        }

        return res;
    }

}