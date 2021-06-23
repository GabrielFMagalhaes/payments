package com.gabrielfmagalhaes.payments.core.transaction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.core.operation.model.Operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class Transaction {

    private UUID id = UUID.randomUUID();
    private Account account;
    private Operation operation;
    private BigDecimal amount;
    private LocalDateTime eventDate = LocalDateTime.now();

    public Transaction(final Account account, final Operation operation, final BigDecimal amount) {
        this.account = account;
        this.operation = operation;
        this.amount = amount;
    }
}
