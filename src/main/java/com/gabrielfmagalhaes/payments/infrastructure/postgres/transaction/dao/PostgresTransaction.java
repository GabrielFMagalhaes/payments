package com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gabrielfmagalhaes.payments.core.transaction.model.Transaction;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.dao.PostgresAccount;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.operation.dao.PostgresOperation;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class PostgresTransaction {
    
    @Id
    private UUID id = UUID.randomUUID();

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
    private PostgresAccount account;

    @OneToOne
    @JoinColumn(name = "operation_type_id", nullable = false, referencedColumnName = "id")
    private PostgresOperation operation;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @CreatedDate
    @Column(name = "event_date", nullable = false, updatable = false)
    private LocalDateTime eventDate = LocalDateTime.now();

    public static PostgresTransaction mapToPostgres(final Transaction transaction) {
        return new PostgresTransaction()
            .withId(transaction.getId())
            .withAccount(PostgresAccount
                .mapToPostgres(transaction.getAccount()))
            .withOperation(PostgresOperation
                .mapToPostgres(transaction.getOperation()))
            .withAmount(transaction.getAmount())
        ;
    }

    public Transaction mapToEntity() {
        return new Transaction()
            .withId(id)
            .withAccount(account.mapToEntity())
            .withOperation(operation.mapToEntity())
            .withAmount(amount)
            .withEventDate(eventDate)
        ;
    }
}
