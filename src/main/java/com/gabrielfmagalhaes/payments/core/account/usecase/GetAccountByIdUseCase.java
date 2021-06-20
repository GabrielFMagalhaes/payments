package com.gabrielfmagalhaes.payments.core.account.usecase;

import com.gabrielfmagalhaes.payments.core.account.Account;

public interface GetAccountByIdUseCase {
    
    Account execute(String id);
}
