package com.karlchu.mo.web.util;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Son Nguyen
 * Date: 4/11/13
 * Time: 2:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil {
    public static String escapeJS(String value) {
        return StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(value));
    }
    public static String replaceLineBreakChar(String input) {
        if (input == null) {
            return "";
        }
        return input.replaceAll("\\n", "<br/>");
    }

    public static String unEscapeHTML(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = StringEscapeUtils.unescapeHtml(value);
        }
        return value;
    }
    public static String escapeHTML(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = StringEscapeUtils.escapeHtml(value);
        }
        return value;
    }
}
