package com.karlchu.cache.utils;

import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.concurrent.TimeUnit;

/**
 * Utility for caching using Infinispan
 * Created by Hieu Le on 9/26/2016.
 */
class InfinispanCacheUtil implements ICacheUtil {
    private Cache<Object, Object> cache;

    public InfinispanCacheUtil(String jndiUri) throws NamingException {
        InitialContext ic = new InitialContext();
        CacheContainer container = (CacheContainer) ic.lookup(jndiUri);
        this.cache = container.getCache();
    }

    @Override
    public void put(Object key, Object value, long expiredTime, TimeUnit unit) {
        if (expiredTime > 0L) {
            cache.put(key, value, expiredTime, unit);
        } else {
            cache.put(key, value);
        }
    }

    @Override
    public Object get(Object key) {
        return cache.get(key);
    }

    @Override
    public void remove(Object key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clearAsync();
    }

    @Override
    public boolean isExists(Object key) {
        return cache.containsKey(key);
    }
}
