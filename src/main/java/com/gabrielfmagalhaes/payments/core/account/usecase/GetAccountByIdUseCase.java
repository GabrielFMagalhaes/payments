package com.gabrielfmagalhaes.payments.core.account.usecase;

import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.model.Account;

public interface GetAccountByIdUseCase {
    
    Account execute(UUID id);
}
