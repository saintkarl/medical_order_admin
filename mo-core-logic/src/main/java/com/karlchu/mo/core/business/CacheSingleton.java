package com.karlchu.mo.core.business;

import javax.ejb.Local;

/**
 * Created by hieu on 8/13/2015.
 */
@Local
public interface CacheSingleton {
    public void clearCache();
    public Object getValue(String key);
    public void putValue(String key, Object value);
    public void remove(String key);
    public void putValue(String key, Object value, int expiredTime);
}