package com.gabrielfmagalhaes.payments.infrastructure.rest.accounts;

import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.AccountResponse;

public interface AccountController {
    public AccountResponse create(CreateAccountRequest request);

    public AccountResponse findById(String id);
}
