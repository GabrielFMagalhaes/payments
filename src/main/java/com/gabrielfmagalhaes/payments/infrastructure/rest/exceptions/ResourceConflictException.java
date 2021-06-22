package com.gabrielfmagalhaes.payments.infrastructure.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflictException extends RuntimeException {

    public ResourceConflictException(final String message) {
        super(message);
    }

    public ResourceConflictException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
