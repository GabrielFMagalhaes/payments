package com.gabrielfmagalhaes.payments.core.account.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountAlreadyExistsException extends RuntimeException {
    
    public AccountAlreadyExistsException(final String message) {
        super(message);
    }
    
    public AccountAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
