package com.gabrielfmagalhaes.payments.core.account.usecase.impl;

import java.util.Optional;

import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountAlreadyExistsException;
import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.CreateAccountUseCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final static Logger logger = LoggerFactory.getLogger(CreateAccountUseCaseImpl.class);

    private final AccountRepositoryUseCase accountRepository;

    @Override
    public Account execute(CreateAccountRequest request) {
        String documentNumber = request.getDocumentNumber();

        Optional<Account> account = accountRepository.findByDocumentNumber(request.getDocumentNumber());

        if (!account.isEmpty()) {
            throw new AccountAlreadyExistsException("Account already exists with document number: " + documentNumber);
        }

        logger.info("Persisting account into database: " + request);
        
        return accountRepository.save(new Account(documentNumber));
    }
    
}
