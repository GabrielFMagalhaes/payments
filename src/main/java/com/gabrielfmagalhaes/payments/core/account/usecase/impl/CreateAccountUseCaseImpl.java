package com.gabrielfmagalhaes.payments.core.account.usecase.impl;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountAlreadyExistsException;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.core.account.usecase.CreateAccountUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    @Override
    public Account execute(CreateAccountRequest request) {
        throw new AccountAlreadyExistsException("Account already exists hehe");
    }
    
}
