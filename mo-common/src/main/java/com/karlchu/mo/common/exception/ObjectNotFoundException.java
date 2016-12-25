package com.karlchu.mo.common.exception;

public class ObjectNotFoundException extends Exception {

	public ObjectNotFoundException(String message) {
        super(message);
	}
    public ObjectNotFoundException(String message, Throwable e) {
        super(message, e);
    }
    public ObjectNotFoundException(Throwable e) {
        super(e);
    }
}
