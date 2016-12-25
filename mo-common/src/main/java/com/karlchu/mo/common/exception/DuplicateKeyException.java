package com.karlchu.mo.common.exception;

/**
 * Created with IntelliJ IDEA.
 * User: viennh
 * Date: 11/15/13
 * Time: 12:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class DuplicateKeyException extends Exception {
    public DuplicateKeyException() {
    }

    public DuplicateKeyException(String message) {
        super(message);
    }

    public DuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateKeyException(Throwable cause) {
        super(cause);
    }

    public DuplicateKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        //super(message, cause, enableSuppression, writableStackTrace);
    }
}
