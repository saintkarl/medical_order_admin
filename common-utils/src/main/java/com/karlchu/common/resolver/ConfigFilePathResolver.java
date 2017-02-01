package com.karlchu.common.resolver;

/**
 * Created by Hieu Le on 9/27/2016.
 */
public interface ConfigFilePathResolver {
    /**
     *
     * @param fileName
     * @return with prefix 'classpath:' if the file in classpath, or with 'file:' if it's a real path of a file. Can be null
     */
    String resolvePath(String fileName);
}
