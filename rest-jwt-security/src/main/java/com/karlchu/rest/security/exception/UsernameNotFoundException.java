package com.karlchu.rest.security.exception;


/**
 * Thrown if an {@link UserDetailsService} implementation cannot locate a {@link UserDetails} by its username.
 *
 * @author Hieu Le
 */

public class UsernameNotFoundException extends Exception {
    public UsernameNotFoundException() {
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }
}
