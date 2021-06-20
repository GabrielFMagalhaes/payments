package com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.convertes;

import com.gabrielfmagalhaes.payments.core.transaction.Transaction;
import com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.response.TransactionResponse;

public class TransactionRestConverter {
    
    public TransactionResponse mapToRest(final Transaction transaction) {
        return new TransactionResponse(
            transaction.getId())
        ;
    }
}