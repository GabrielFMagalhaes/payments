package com.gabrielfmagalhaes.payments.core.account.usecase;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;

public interface CreateAccountUseCase {
    
    Account execute(CreateAccountRequest request);
}
