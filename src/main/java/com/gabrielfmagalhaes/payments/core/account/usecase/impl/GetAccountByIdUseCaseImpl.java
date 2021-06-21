package com.gabrielfmagalhaes.payments.core.account.usecase.impl;

import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.GetAccountByIdUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetAccountByIdUseCaseImpl implements GetAccountByIdUseCase {

    private final AccountRepositoryUseCase accountRepository;
    
    @Override
    public Account execute(UUID id) {
        return accountRepository.findById(id)
            .orElseThrow(() -> new AccountNotFoundException("No account was found with id: " + id));
    }
    
}
