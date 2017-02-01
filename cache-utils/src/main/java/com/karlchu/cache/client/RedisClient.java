/*
 * Copyright (c) 2016. Sunghyouk Bae <sunghyouk.bae@gmail.com>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.karlchu.cache.client;


import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * RedisClient implemented using Redisson library
 * <p>
 * see https://github.com/mrniko/redisson
 *
 * @author Hieu Le
 */
public class RedisClient {
  public static final int DEFAULT_EXPIRY_IN_SECONDS = 120;

  private final RedissonClient redisson;

  private int expiryInSeconds;

  public RedisClient() {
    this(Redisson.create(), DEFAULT_EXPIRY_IN_SECONDS);
  }

  public RedisClient(RedissonClient redisson, int expiryInSeconds) {
    this.redisson = redisson;

    if (expiryInSeconds >= 0) {
      this.expiryInSeconds = expiryInSeconds;
    }
  }

  public long dbSize() {
    return redisson.getKeys().count();
  }

  public boolean exists(final String region, final Object key) {
    return getCache(region).containsKey(key);
  }

  @SuppressWarnings("unchecked")
  public <T> T get(final String region, final Object key) {
    T cacheItem = (T) getCache(region).get(key);
    return cacheItem;
  }

  public boolean isExpired(final String region, final Object key) {
    return exists(region, key);
  }

  public Set<Object> keysInRegion(final String region) {
    return getCache(region).keySet();
  }

  public long keySizeInRegion(final String region) {
    return getCache(region).size();
  }


  public Map<Object, Object> getAll(final String region) {
    return getCache(region);
  }

  public void set(final String region, final Object key, Object value) {
    set(region, key, value, expiryInSeconds);
  }

  public void set(final String region, final Object key, Object value, final long timeoutInSeconds) {
    set(region, key, value, timeoutInSeconds, TimeUnit.SECONDS);
  }

  /**
   * Stores value mapped by key with specified time to live, if the timeout is zero, it'll be cached permanent
   * @param region
   * @param key
   * @param value
   * @param timeout
   * @param unit
   */
  public void set(final String region, final Object key, Object value, final long timeout, final TimeUnit unit) {
    RMapCache<Object, Object> cache = redisson.getMapCache(region);
    if (timeout > 0L) {
      cache.fastPut(key, value, timeout, unit);
    } else {
      cache.fastPut(key, value);
    }
  }

  public void expire(final String region) {
    getCache(region).clearExpire();
  }

  public void del(final String region, final Object key) {
    getCache(region).fastRemove(key);
  }

  public void mdel(final String region, final Collection<?> keys) {
    getCache(region).fastRemove(keys.toArray(new Object[keys.size()]));
  }

  public void deleteRegion(final String region) {
    getCache(region).clear();
  }

  public void flushDb() {
    redisson.getKeys().flushdb();
  }

  public boolean isShutdown() {
    return redisson.isShutdown();
  }

  public void shutdown() {
    redisson.shutdown();
  }

  private RMapCache<Object, Object> getCache(final String region) {
    return redisson.getMapCache(region);
  }
}
