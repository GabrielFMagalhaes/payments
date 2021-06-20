package com.gabrielfmagalhaes.payments.infrastructure.rest.transaction;

import com.gabrielfmagalhaes.payments.core.transaction.ports.incoming.CreateTransactionRequest;
import com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.response.TransactionResponse;

public interface TransactionController {
    public TransactionResponse create(CreateTransactionRequest request);
}
