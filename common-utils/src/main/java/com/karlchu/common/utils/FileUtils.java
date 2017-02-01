package com.karlchu.common.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hieu Le on 9/27/2016.
 */
public class FileUtils {
    private static Logger log = Logger.getLogger(FileUtils.class);

    public static final String FILE_URL_PREFIX = "file:";
    public static final String RESOURCE_URL_PREFIX = "classpath:";
    /**
     * Open outer file or resource file
     *
     * @param path file path
     * @return Input stream, null if not exists
     * @throws IOException
     */
    public static InputStream getFileInputStream(String path) throws IOException {
        InputStream is = null;
        if (path != null) {
            if (path.startsWith(FILE_URL_PREFIX)) {
                String filepath = path.substring(FILE_URL_PREFIX.length());
                File file = new File(filepath);
                if (file.isFile()) {
                    is = new FileInputStream(file);
                }
            } else if (path.startsWith(RESOURCE_URL_PREFIX)) {
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.substring(RESOURCE_URL_PREFIX.length()));
            } else {
                // load from resources
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            }
        }
        return is;
    }

    public static Object getClassInstance(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (Exception e) {
            log.error("Could not create a new instance for " + className);
        }
        return null;
    }
}
