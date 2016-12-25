package com.karlchu.mo.web.util;

import com.karlchu.mo.common.util.CommonUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * Copyright (c) by Ban Vien Co., Ltd.
 * User: Vien Nguyen
 * Date: 4/10/13
 * Time: 3:11 AM
 */
public class Config extends Properties {
    private transient final Logger log = Logger.getLogger(getClass());

    private static Config ourInstance = new Config();
    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {
        File configFile = CommonUtil.getConfigFile("retirement/appconfig.properties");
        if(configFile != null) {
            try {
                InputStream input = new FileInputStream(configFile);
                Properties serverProperties = new Properties();
                serverProperties.load(input);
                //override the web config
                Set<String> propertyNames = serverProperties.stringPropertyNames();
                for (String key: propertyNames) {
                    this.put(key,serverProperties.get(key));
                }
            } catch (FileNotFoundException e) {
                log.error("Not found retirement/appconfig.properties file from server configuration. Please check it");
            } catch (IOException e) {
                log.error("Could not load retirement/appconfig.properties file from server configuration. Please check it");
            }
        }
    }

}
