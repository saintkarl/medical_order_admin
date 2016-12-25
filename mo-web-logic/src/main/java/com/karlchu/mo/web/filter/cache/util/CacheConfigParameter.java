package com.karlchu.mo.web.filter.cache.util;

/**
 * Enumeration of the possible configuration parameters.
 * 
 * @author Vien Nguyen
 */
public enum CacheConfigParameter {
    /** Defines whether a component is static or not. */
    STATIC("static"),
    /** Cache directive to control where the response may be cached. */
    PRIVATE("private"),
    /** Cache directive to set an expiration date relative to the current date. */
    EXPIRATION_TIME("expirationTime");

    private String name;

    private CacheConfigParameter(String name) {
        this.name = name;
    }

    /**
     * Gets the parameter name.
     * 
     * @return the parameter name
     */
    public String getName() {
        return this.name;
    }
}
