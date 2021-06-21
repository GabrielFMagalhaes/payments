package com.gabrielfmagalhaes.payments.core.transaction.usecase.impl;

import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.Transaction;
import com.gabrielfmagalhaes.payments.core.transaction.ports.incoming.CreateTransactionRequest;
import com.gabrielfmagalhaes.payments.core.transaction.ports.outgoing.TransactionRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.CreateTransactionUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final TransactionRepositoryUseCase transactionRepositoryUseCase;
    
    private final AccountRepositoryUseCase accountRepositoryUseCase;

    @Override
    public Transaction execute(CreateTransactionRequest request) {
        UUID accountUUID = UUID.fromString(request.getAccountId());

        accountRepositoryUseCase.findById(accountUUID)
            .orElseThrow(() -> new AccountNotFoundException("No account was found with id: " + accountUUID));
        
        return transactionRepositoryUseCase.save(
            new Transaction(
                accountUUID, 
                request.getOperationTypeId(), 
                request.getAmount()))
        ;
    }
}
