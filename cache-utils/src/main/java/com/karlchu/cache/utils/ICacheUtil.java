package com.karlchu.cache.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Hieu Le on 9/26/2016.
 */
interface ICacheUtil {
    void put(Object key, Object value, long expiredTime, TimeUnit unit);
    Object get(Object key);
    void remove(Object key);
    void clear();
    boolean isExists(Object key);
}
