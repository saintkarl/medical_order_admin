package com.karlchu.rest.security.utils;


import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

public final class RequestUtil {

    public static boolean isMatchWildcard(String pattern, String data) {
        if(StringUtils.isNotBlank(data)) {
            WildcardHelper wh = new WildcardHelper();
            String rawPattern = StringUtils.trim(pattern).toLowerCase();
            int[] compiledPattern = wh.compilePattern(rawPattern);
            return wh.match(new HashMap(), data.toLowerCase(), compiledPattern);
        }
        return false;
    }

    public static boolean isMatchWildcards(String[] patterns, String data) {
        if(patterns != null) {
            for(String pattern : patterns) {
                if(isMatchWildcard(pattern, data)) {
                    return true;
                }
            }
        }
        return false;
    }
}
