package com.gabrielfmagalhaes.payments.core.account.usecase.impl;

import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.GetAccountByIdUseCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetAccountByIdUseCaseImpl implements GetAccountByIdUseCase {

    private final static Logger logger = LoggerFactory.getLogger(GetAccountByIdUseCaseImpl.class);
    
    private final AccountRepositoryUseCase accountRepository;
    
    @Override
    public Account execute(UUID id) {
        logger.info("Finding account by id from database: " + id);

        return accountRepository.findById(id)
            .orElseThrow(() -> new AccountNotFoundException("No account was found with id: " + id));
    }
    
}
