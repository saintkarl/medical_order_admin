package com.karlchu.common.resolver;

/**
 * Created by Hieu Le on 9/27/2016.
 */
public class ClasspathConfigFilePathResolver implements ConfigFilePathResolver {
    /**
     *
     * @param fileName
     * @return classpath fitted the format using for resolver
     */
    @Override
    public String resolvePath(String fileName) {
        if (fileName != null && !fileName.startsWith("classpath:")) {
            return "classpath:" + fileName;
        }
        return fileName;
    }
}
