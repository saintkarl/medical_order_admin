package com.karlchu.cache.utils;

import com.karlchu.common.utils.FileUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Hieu Le on 9/26/2016.
 */

public class CacheConfig extends Properties {
    private Logger log = Logger.getLogger(CacheConfig.class);
    public static final String EXPECTATION_CONFIG_FILE = "bv-appcache.properties";
    public static final String DEF_CONFIG_FILE = "def-bv-appcache.properties";
    public static final String CACHE_PROVIDER_REDIS = "redis";
    public static final String CACHE_PROVIDER_INFINISPAN = "infinispan";
    public static final int DEFAULT_EXPIRY_IN_SECONDS = 1800;

    private final String CACHE_JNDI_URI = "cache.jndi.uri";
    private final String EXPIRATION_SECS = "cache.expiryInSeconds";
    private final String CACHE_PROVIDER = "cache.provider";
    private final String REDISSON_CONFIG_FILE = "cache.redisson.config";
    private final String CACHE_CONFIG_FILE_PATH_RESOLVER = "cache.file.path.resolver";

    private static CacheConfig instance;

    private CacheConfig() {

    }

    public static CacheConfig getInstance() {
        if (instance == null) {
            instance = new CacheConfig();
        }

        return instance;
    }

    public String getCacheJndiUri() {
        return getProperty(CACHE_JNDI_URI);
    }

    public String getConfigFilePathResolver() {
        return getProperty(CACHE_CONFIG_FILE_PATH_RESOLVER);
    }

    public String getCacheProvider() {
        return getProperty(CACHE_PROVIDER);
    }

    public String getRedissonConfigFile() {
        return getProperty(REDISSON_CONFIG_FILE);
    }

    public int getExpirationInSeconds() {
        return Integer.parseInt(getProperty(EXPIRATION_SECS, String.valueOf(DEFAULT_EXPIRY_IN_SECONDS)));
    }

    public synchronized void checkAndLoadConfigFile(boolean isForce) throws IOException {
        if (isForce || isEmpty()) {
            log.info("Cache config is empty, begin loading config...");
            InputStream is = FileUtils.getFileInputStream(EXPECTATION_CONFIG_FILE);
            if (is == null) {
                log.info("NOT found expected cache config file '" + EXPECTATION_CONFIG_FILE + "', will load default cache config");
                is = FileUtils.getFileInputStream(DEF_CONFIG_FILE);
                if (is == null) {
                    throw new IOException("NOT found default cache config");
                }
                load(is);
            } else {
                load(is);
                log.info("Found cache config file");
            }
        }
    }

    public synchronized void checkAndLoadConfigFile() throws IOException {
        checkAndLoadConfigFile(false);
    }

}
