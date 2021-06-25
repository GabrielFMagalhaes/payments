package com.gabrielfmagalhaes.payments.infrastructure.postgres.account.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gabrielfmagalhaes.payments.core.account.model.Account;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class PostgresAccount {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @Column(name = "available_credit_limit", nullable = false)
    private BigDecimal availableCreditLimit = BigDecimal.ZERO;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    public static PostgresAccount mapToPostgres(final Account account) {
        return new PostgresAccount()
            .withId(account.getId())
            .withDocumentNumber(account.getDocumentNumber())
            .withAvailableCreditLimit(account.getAvailableCreditLimit())
        ;
    }

    public Account mapToEntity() {
        return new Account()
            .withId(id)
            .withDocumentNumber(documentNumber)
            .withAvailableCreditLimit(availableCreditLimit)
            .withCreatedAt(createdAt)
            .withUpdatedAt(updatedAt)
        ;
    }
}
