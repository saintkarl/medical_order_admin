/**
 * Cache utility - Singleton to monitor the GerneralCacheAdministrator from Infinispan Cache to provide the APIs for caching objects
 */
package com.karlchu.mo.common.util;

import com.karlchu.mo.common.Constants;
import org.apache.log4j.Logger;
import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Nguyen Hai Vien
 *
 */
public class CacheUtil {
    private transient final Logger logger = Logger.getLogger(CacheUtil.class);
    private static CacheUtil instance;

    private Cache<String, Object> cache;
    private int time = 1800;
    private List<String> cacheKeys;

    private CacheUtil() {
        try {
            InitialContext ic = new InitialContext();
            CacheContainer container = (CacheContainer) ic.lookup(Constants.PLATFORM_CACHE_JNDI_URI);
            this.cache = container.getCache();
        } catch (NamingException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static CacheUtil getInstance() {
        if (instance == null) {
            instance = new CacheUtil();
        }
        return instance;
    }

    public void clearCache() {
        this.cache.clear();
    }

    public Object getValue(String key) {
        return cache.get(key);
    }
    /**
     *
     * @param key
     * @param value
     */
    public void putValue(String key, Object value) {
        this.putValue(key, value, time);
    }

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

    public List<String> getCacheKeys() {
        return new ArrayList<>(cache.keySet());
    }

    public void setCacheKeys(List<String> cacheKeys) {
        this.cacheKeys = cacheKeys;
    }
}
