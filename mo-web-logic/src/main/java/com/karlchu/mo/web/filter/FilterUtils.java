package com.karlchu.mo.web.filter;

import com.karlchu.mo.web.util.RequestUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Created by hieu on 11/11/2015.
 */
public class FilterUtils {
    public static final String IGNORE_LIST_PARAM_NAME = "domainNameFilterIgnoreList";

    public static boolean isIgnore(String ignoreList, String uri) {
        boolean res = false;
        if (StringUtils.isNotBlank(ignoreList)) {
            String[] patterns = ignoreList.split(",");
            res = RequestUtil.isMatchWildcards(patterns, uri);
        }
        if (!res && uri.startsWith("/WEB-INF/")) {
            res = true;
        }
        return res;
    }
}
