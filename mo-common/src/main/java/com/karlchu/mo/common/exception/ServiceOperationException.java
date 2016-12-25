package com.karlchu.mo.common.exception;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 11/16/13
 * Time: 3:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceOperationException extends Exception{
    public ServiceOperationException(String message) {
        super(message);
    }
    public ServiceOperationException(String message, Throwable e) {
        super(message, e);
    }
    public ServiceOperationException(Throwable e) {
        super(e);
    }
}
