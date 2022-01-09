package com.sbrf.reboot.repository.impl;

public class AccountRepositoryException extends Exception {
    public AccountRepositoryException(String message) {
        super(message);
    }

    public AccountRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
