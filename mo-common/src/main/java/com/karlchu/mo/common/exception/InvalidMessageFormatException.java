package com.karlchu.mo.common.exception;

import javax.ejb.ApplicationException;

/**
 * User: hieu
 * Date: 8/6/11
 * Time: 3:49 PM
 */
@ApplicationException(rollback = true)
public class InvalidMessageFormatException extends Exception {


	public InvalidMessageFormatException(final String message) {
		super(message);
	}

	public InvalidMessageFormatException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
