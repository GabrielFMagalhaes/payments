package com.gabrielfmagalhaes.payments.infrastructure.rest;

import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.infrastructure.rest.response.AccountResponse;

public interface AccountController {
    public AccountResponse create(CreateAccountRequest request);

    public AccountResponse findById(String id);
}
