package com.gabrielfmagalhaes.payments.core.account.usecase;

import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountLimitRequest;

public interface UpdateAccountLimitUseCase {
    Account execute(UUID id, CreateAccountLimitRequest createAccountLimitRequest);
}
