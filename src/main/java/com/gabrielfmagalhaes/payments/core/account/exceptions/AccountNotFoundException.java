package com.gabrielfmagalhaes.payments.core.account.exceptions;

public class AccountNotFoundException extends RuntimeException {
    
    public AccountNotFoundException(final String message) {
        super(message);
    }
}
