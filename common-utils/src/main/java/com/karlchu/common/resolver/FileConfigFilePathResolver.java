package com.karlchu.common.resolver;

/**
 * Created by Hieu Le on 9/27/2016.
 */
public class FileConfigFilePathResolver implements ConfigFilePathResolver {
    /**
     *
     * @param fileName
     * @return file path fitted the format using for resolver
     */
    @Override
    public String resolvePath(String fileName) {
        if (fileName != null && !fileName.startsWith("file:")) {
            return "file:" + fileName;
        }
        return fileName;
    }
}
