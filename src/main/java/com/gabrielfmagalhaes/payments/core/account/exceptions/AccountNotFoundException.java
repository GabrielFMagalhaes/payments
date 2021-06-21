package com.gabrielfmagalhaes.payments.core.account.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountNotFoundException extends RuntimeException {
    
    public AccountNotFoundException(final String message) {
        super(message);
    }

    public AccountNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
