package com.gabrielfmagalhaes.payments.core.operation.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidOperationTypeException extends RuntimeException {
    
    public InvalidOperationTypeException(final String message) {
        super(message);
    }
    
    public InvalidOperationTypeException(final String message, final Throwable cause) {
        super(message, cause);
    }

}