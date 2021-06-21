package com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gabrielfmagalhaes.payments.core.transaction.Transaction;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "Account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class TransactionDao {
    

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(name = "operation_type_id", nullable = false)
    private int operationTypeId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @CreatedDate
    @Column(name = "event_date", nullable = false, updatable = false)
    private LocalDateTime eventDate;

    public static TransactionDao mapToDao(final Transaction transaction) {
        return new TransactionDao()
            .withId(transaction.getId())
            .withAccountId(transaction.getAccountId())
            .withOperationTypeId(transaction.getOperationTypeId())
            .withAmount(transaction.getAmount())
        ;
    }

    public Transaction mapToEntity() {
        return new Transaction(id, accountId, operationTypeId, amount, eventDate);
    }
}
