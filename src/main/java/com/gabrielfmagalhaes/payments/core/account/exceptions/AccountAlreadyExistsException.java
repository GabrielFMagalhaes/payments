package com.gabrielfmagalhaes.payments.core.account.exceptions;

public class AccountAlreadyExistsException extends RuntimeException {
    
    public AccountAlreadyExistsException(final String message) {
        super(message);
    }
}
