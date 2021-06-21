package com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.repository.impl;

import com.gabrielfmagalhaes.payments.core.transaction.Transaction;
import com.gabrielfmagalhaes.payments.core.transaction.ports.outgoing.TransactionRepositoryUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.dao.TransactionDao;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.repository.TransactionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepositoryUseCase {

    private TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(TransactionDao.mapToDao(transaction)).mapToEntity();
    }

}
