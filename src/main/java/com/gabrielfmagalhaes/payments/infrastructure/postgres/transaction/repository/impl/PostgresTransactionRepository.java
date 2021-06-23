package com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.repository.impl;

import com.gabrielfmagalhaes.payments.core.transaction.model.Transaction;
import com.gabrielfmagalhaes.payments.core.transaction.ports.outgoing.TransactionRepositoryUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.dao.PostgresTransaction;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.repository.PostgresSpringDataTransactionRepository;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class PostgresTransactionRepository implements TransactionRepositoryUseCase {

    private final PostgresSpringDataTransactionRepository postgresSpringDataTransactionRepository;

    @Override
    public Transaction save(Transaction transaction) {
        return postgresSpringDataTransactionRepository.save(
            PostgresTransaction.mapToPostgres(transaction)).mapToEntity();
    }

}
