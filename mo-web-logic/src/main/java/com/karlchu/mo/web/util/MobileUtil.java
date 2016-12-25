package com.karlchu.mo.web.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (c) by Ban Vien Co., Ltd.
 * User: Thoai Bui
 * Date: 5/12/13
 * Time: 6:56 PM
 */
public class MobileUtil {
    private static transient final Log log = LogFactory.getLog(MobileUtil.class);
    /**
     * Check current request is from a tablet device
     * @param request
     * @return
     */
    public static boolean isTablet(ServletRequest request) {
        try {
            String userAgent = ((HttpServletRequest)request).getHeader("User-Agent");
            return StringUtils.isNotBlank(userAgent) && (userAgent.toLowerCase().contains("ipad")||userAgent.toLowerCase().contains("android"));
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public static boolean isAndroidTablet(ServletRequest request) {
        try {
            String userAgent = ((HttpServletRequest)request).getHeader("User-Agent");
            return StringUtils.isNotBlank(userAgent) && userAgent.toLowerCase().contains("android");
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public static boolean isSurfaceTablet(ServletRequest request) {
        try {
            String userAgent = ((HttpServletRequest)request).getHeader("User-Agent");
            return StringUtils.isNotBlank(userAgent) && userAgent.contains("MSIE 10.0") && userAgent.contains("Touch");
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    /**
     * Check current request is from a mobile device
     * @param request
     * @return
     */
    public static boolean isMobile(ServletRequest request) {
        try {
            Device device = (Device)request.getAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE);
            if (device != null) {
                return device.isMobile();
            }
            String userAgent = ((HttpServletRequest)request).getHeader("User-Agent");
            return StringUtils.isNotBlank(userAgent) && userAgent.toLowerCase().contains("iphone");
        }
        catch (Exception e) {
            //ignore
            //log.error(e.getMessage());
        }
        return false;
    }

    /**
     * Check current request is from a mobile or tablet device
     * @param request
     * @return
     */
    public static boolean isMobileOrTablet(ServletRequest request) {
        try {
            Device device = (Device)request.getAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE);
            String userAgent = ((HttpServletRequest)request).getHeader("User-Agent");
            if (userAgent == null) {
                userAgent = "";
            }
            boolean isTablet = userAgent.toLowerCase().contains("ipad");
            boolean isMobile = device != null ? device.isMobile() : userAgent.toLowerCase().contains("iphone");
            boolean isAndroid = userAgent.toLowerCase().contains("android");
            return (isMobile || isTablet || isAndroid);
        }
        catch (Exception e) {
        }
        return false;
    }
}
