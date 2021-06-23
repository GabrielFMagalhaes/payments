package com.gabrielfmagalhaes.payments.core.operation.exceptions;

public class InvalidOperationTypeException extends RuntimeException {
    
    public InvalidOperationTypeException(final String message) {
        super(message);
    }
}