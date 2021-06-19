package com.gabrielfmagalhaes.payments.infrastructure.rest;

import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.infrastructure.rest.response.CreateAccountResponse;

public interface AccountController {
    public CreateAccountResponse create(CreateAccountRequest request);
}
