package com.karlchu.mo.common.exception;

/**
 * Created with IntelliJ IDEA.
 * User: viennh
 * Date: 11/15/13
 * Time: 12:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class DuplicateItemException extends Exception {
    public DuplicateItemException() {
    }

    public DuplicateItemException(String message) {
        super(message);
    }

    public DuplicateItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateItemException(Throwable cause) {
        super(cause);
    }

    public DuplicateItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        //super(message, cause, enableSuppression, writableStackTrace);
    }
}
