package com.karlchu.cache.utils;

import com.karlchu.cache.client.RedisClient;
import com.karlchu.common.utils.FileUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * Utility for caching using Redis
 * Created by Hieu Le on 9/26/2016.
 */
class RedisCacheUtil implements ICacheUtil {
    public static final String REGION_NAME = "APP_CACHE";

    private RedisClient redisClient;

    public RedisCacheUtil(String configFile, int expiryInSeconds) throws IOException {
        redisClient = new RedisClient(createRedisson(configFile), expiryInSeconds);
    }

    @Override
    public void put(Object key, Object value, long expiredTime, TimeUnit unit) {
        redisClient.set(REGION_NAME, key, value, expiredTime, unit);
    }

    @Override
    public Object get(Object key) {
        return redisClient.get(REGION_NAME, key);
    }

    @Override
    public void remove(Object key) {
        redisClient.del(REGION_NAME, key);
    }

    @Override
    public void clear() {
        redisClient.deleteRegion(REGION_NAME);
    }

    @Override
    public boolean isExists(Object key) {
        return redisClient.exists(REGION_NAME, key);
    }

    private RedissonClient createRedisson(String configFile) throws IOException {

        InputStream is = FileUtils.getFileInputStream(configFile);
        if (is == null) {
            throw new IOException("NOT found Redisson cache config for Redis");
        }
        Config config = Config.fromYAML(is);

        return Redisson.create(config);
    }
}
