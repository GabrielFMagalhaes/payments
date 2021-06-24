package com.gabrielfmagalhaes.payments.core.transaction.ports.outgoing;

import com.gabrielfmagalhaes.payments.core.transaction.model.Transaction;

public interface TransactionRepositoryUseCase {
    Transaction save(Transaction transaction);
}