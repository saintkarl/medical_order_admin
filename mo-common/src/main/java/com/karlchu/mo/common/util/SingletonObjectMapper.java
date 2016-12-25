package com.karlchu.mo.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created with IntelliJ IDEA.
 * User: TriLe
 * Date: 5/16/16
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingletonObjectMapper extends ObjectMapper {
    private SingletonObjectMapper() {}

    private static class LazyHolder {
        private static final SingletonObjectMapper INSTANCE = new SingletonObjectMapper();
    }

    public static SingletonObjectMapper getInstance() {
        return LazyHolder.INSTANCE;
    }
}
