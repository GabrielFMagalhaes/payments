package com.gabrielfmagalhaes.payments.core.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private UUID id = UUID.randomUUID();
    private UUID accountId;
    private int operationTypeId;
    private BigDecimal amount;
    private LocalDateTime eventDate;

    public Transaction(final UUID accountId, final int operationTypeId, final BigDecimal amount) {
        this.accountId = accountId;
        this.operationTypeId = operationTypeId;
        this.amount = amount;
    }
}
