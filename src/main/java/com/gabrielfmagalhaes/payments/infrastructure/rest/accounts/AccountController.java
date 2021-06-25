package com.gabrielfmagalhaes.payments.infrastructure.rest.accounts;

import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountLimitRequest;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.AccountLimitResponse;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.AccountResponse;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.CreateAccountResponse;

public interface AccountController {
    public CreateAccountResponse create(CreateAccountRequest request);

    public AccountResponse findById(String id);

    public AccountLimitResponse findAvailableCreditLimitById(String id);

    public AccountLimitResponse updateLimit(String id, CreateAccountLimitRequest createAccountLimitRequest);
}
