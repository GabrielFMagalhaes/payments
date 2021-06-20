package com.gabrielfmagalhaes.payments.core.transaction.usecase.impl;


import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.usecase.GetAccountByIdUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.Transaction;
import com.gabrielfmagalhaes.payments.core.transaction.ports.incoming.CreateTransactionRequest;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.CreateTransactionUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final GetAccountByIdUseCase getAccountByIdUseCase;

    @Override
    public Transaction execute(CreateTransactionRequest request) {
        Account account = getAccountByIdUseCase.execute(request.getAccountId());

        // if (account == null) {
        //     throw new AccountNotFoundException("No account was found with account id: " + request.getAccountId());
        // }

        return new Transaction();

    }
    
}
