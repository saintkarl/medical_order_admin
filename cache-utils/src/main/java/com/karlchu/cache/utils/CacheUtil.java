package com.karlchu.cache.utils;

import com.karlchu.common.resolver.ConfigFilePathResolver;
import com.karlchu.common.utils.FileUtils;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Cache util for multiple cache providers, currently support for Infinispan and Redis
 * Created by Hieu Le on 9/26/2016.
 */
public class CacheUtil {
    private transient final Logger logger = Logger.getLogger(CacheUtil.class);

    private static CacheUtil instance;
    private ICacheUtil cache;
    private int expiryInSeconds;

    public static CacheUtil getInstance() {
        if (instance == null) {
            instance = new CacheUtil();
        }
        return instance;
    }

    private CacheUtil() {
        try {
            CacheConfig.getInstance().checkAndLoadConfigFile();
            expiryInSeconds = CacheConfig.getInstance().getExpirationInSeconds();

            String cacheProvider = CacheConfig.getInstance().getCacheProvider();
            if (CacheConfig.CACHE_PROVIDER_REDIS.equals(cacheProvider)) {
                String configFile = CacheConfig.getInstance().getRedissonConfigFile();
                String filePathResolverClassName = CacheConfig.getInstance().getConfigFilePathResolver();
                if (filePathResolverClassName != null && !filePathResolverClassName.trim().equals("")) {
                    ConfigFilePathResolver pathResolver = (ConfigFilePathResolver) FileUtils.getClassInstance(filePathResolverClassName);
                    if (pathResolver != null) {
                        configFile = pathResolver.resolvePath(configFile);
                    }
                }

                cache = new RedisCacheUtil(configFile, expiryInSeconds);
                logger.info("Using Redis for caching");
            } else if (CacheConfig.CACHE_PROVIDER_INFINISPAN.equals(cacheProvider)){
                cache = new InfinispanCacheUtil(CacheConfig.getInstance().getCacheJndiUri());
                logger.info("Using Infinispan for caching");
            } else {
                logger.info("NOT found any cache providers for '" + cacheProvider + "'");
            }
        } catch (Exception e) {
            logger.error("COULD NOT create cache instance", e);
        }
    }

    /**
     * Clear all cache values
     */
    public void clearAll() {
        this.cache.clear();
    }

    /**
     * Get value from cache
     * @param key
     * @return
     */
    public Object getValue(String key) {
        return cache.get(key);
    }
    /**
     * Put value to cache
     * @param key
     * @param value
     */
    public void putValue(String key, Object value) {
        this.putValue(key, value, expiryInSeconds);
    }

    /**
     * Remove value from cache
     * @param key
     */
    public void remove(String key) {
        cache.remove(key);
    }
    /**
     * Put object in cache with specified expiration time in second
     * @param key
     * @param value
     * @param expiredTime - Expiration time in second
     */

    public void putValue(String key, Object value, int expiredTime) {
        cache.put(key, value, expiredTime, TimeUnit.SECONDS);
    }

    /**
     * Check exists key in cache
     * @param key
     */
    public boolean isExists(String key) {
        return cache.isExists(key);
    }
}
