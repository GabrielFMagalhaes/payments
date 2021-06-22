package com.gabrielfmagalhaes.payments.core.transaction.usecase.impl;

import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.operation.Operation;
import com.gabrielfmagalhaes.payments.core.operation.exceptions.InvalidOperationTypeException;
import com.gabrielfmagalhaes.payments.core.operation.ports.outgoing.OperationRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.Transaction;
import com.gabrielfmagalhaes.payments.core.transaction.ports.incoming.CreateTransactionRequest;
import com.gabrielfmagalhaes.payments.core.transaction.ports.outgoing.TransactionRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.CreateTransactionUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final TransactionRepositoryUseCase transactionRepositoryUseCase;
    
    private final AccountRepositoryUseCase accountRepositoryUseCase;

    private final OperationRepositoryUseCase operationRepositoryUseCase;

    @Override
    public Transaction execute(CreateTransactionRequest request) {
        UUID accountUUID = UUID.fromString(request.getAccountId());
        Long operationId = request.getOperationTypeId();

        Account account = accountRepositoryUseCase.findById(accountUUID)
            .orElseThrow(() -> new AccountNotFoundException("No account was found with id: " + accountUUID));
        
        Operation operation = operationRepositoryUseCase.findById(operationId)
            .orElseThrow(() -> new InvalidOperationTypeException("No account was found with id: " + operationId));    

        return transactionRepositoryUseCase.save(
            new Transaction(
                account, 
                operation, 
                request.getAmount()))
        ;
    }
}
