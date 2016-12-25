package com.karlchu.mo.web.util;

import com.karlchu.mo.common.util.CommonUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;

/**
 * Overriding PropertyConfigurer to make the application switchable using properties files
 *
 * @author viennh
 */
public class MyPropertyConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {
    public MyPropertyConfigurer() {
        super();
        File configFile = CommonUtil.getConfigFile("retirement/appconfig.properties");  //E:/WORK/FCV_Retailer/ApplicationFiles/jboss/retirement
        setLocations(new Resource[]{ new FileSystemResource(configFile)});
    }
}
