package com.karlchu.rest.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by Hieu Le on 6/28/2016.
 */
public class JwtUtils {
    private static final Logger logger = Logger.getLogger(JwtUtils.class);

    private static byte[] secretBytes = "EcoKSec1246".getBytes();

    public static String getJwt(Map<String, Object> claims) {
        return getJwt("User", claims, SignatureAlgorithm.HS256);
    }

    public static String getJwt(String subject, Map<String, Object> claims) {
        return getJwt(subject, claims, SignatureAlgorithm.HS256);
    }

    public static String getJwt(String subject, Map<String, Object> claims, SignatureAlgorithm algorithm) {
        return Jwts.builder().setSubject(subject)
                .setIssuer("BanVien")
                .setClaims(claims)
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