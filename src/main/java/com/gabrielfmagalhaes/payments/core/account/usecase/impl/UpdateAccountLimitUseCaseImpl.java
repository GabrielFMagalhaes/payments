package com.gabrielfmagalhaes.payments.core.account.usecase.impl;

import java.util.Optional;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountLimitRequest;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.UpdateAccountLimitUseCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateAccountLimitUseCaseImpl implements UpdateAccountLimitUseCase {

    private final static Logger logger = LoggerFactory.getLogger(CreateAccountUseCaseImpl.class);

    private final AccountRepositoryUseCase accountRepository;

    @Override
    public Account execute(UUID id, CreateAccountLimitRequest createAccountLimitRequest) {
        
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new AccountNotFoundException("No account was found with id: " + id));

        account.setAvailableCreditLimit(createAccountLimitRequest.getAvailableCreditLimit());

        logger.info("Updating account limit: " + account);
        
        return accountRepository.save(account);
    }
    
}
