package com.gabrielfmagalhaes.payments.core.transaction.ports.outgoing;

import com.gabrielfmagalhaes.payments.core.transaction.Transaction;

public interface TransactionRepositoryUseCase {
    Transaction save(Transaction account);
}