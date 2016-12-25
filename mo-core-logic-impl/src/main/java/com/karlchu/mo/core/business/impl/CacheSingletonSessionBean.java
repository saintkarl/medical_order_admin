package com.karlchu.mo.core.business.impl;


import com.karlchu.mo.common.Constants;
import com.karlchu.mo.core.business.CacheSingleton;
import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.concurrent.TimeUnit;

/**
 * Created by hieu on 8/13/2015.
 */
@Startup
@Singleton(name = "CacheSingleton")
public class CacheSingletonSessionBean implements CacheSingleton {
    @Resource(lookup = Constants.PLATFORM_CACHE_JNDI_URI)
    private CacheContainer container;

    private Cache<String, Object> cache;
    private int time = 1800;

    @PostConstruct
    public void start() {
        this.cache = this.container.getCache();
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
}
