package com.gabrielfmagalhaes.payments.core.transaction.usecase;

import com.gabrielfmagalhaes.payments.core.transaction.model.Transaction;
import com.gabrielfmagalhaes.payments.core.transaction.ports.incoming.CreateTransactionRequest;

public interface CreateTransactionUseCase {
    
    Transaction execute(CreateTransactionRequest request);
}
