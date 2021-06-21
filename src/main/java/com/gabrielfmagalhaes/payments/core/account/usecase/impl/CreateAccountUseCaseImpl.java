package com.gabrielfmagalhaes.payments.core.account.usecase.impl;

import java.util.Optional;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountAlreadyExistsException;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.CreateAccountUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepositoryUseCase accountRepository;

    @Override
    public Account execute(CreateAccountRequest request) {
        String documentNumber = request.getDocumentNumber();

        Optional<Account> account = accountRepository.findByDocumentNumber(request.getDocumentNumber());

        if (!account.isEmpty()) {
            throw new AccountAlreadyExistsException("Account already exists with document number: " + documentNumber);
        }

        return accountRepository.save(new Account(documentNumber));
    }
    
}
