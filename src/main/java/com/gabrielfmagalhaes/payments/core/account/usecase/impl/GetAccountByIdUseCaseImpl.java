package com.gabrielfmagalhaes.payments.core.account.usecase.impl;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.usecase.GetAccountByIdUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetAccountByIdUseCaseImpl implements GetAccountByIdUseCase {

    @Override
    public Account execute(String id) {
        throw new AccountNotFoundException("No account was found with id: " + id);
    }
    
}
