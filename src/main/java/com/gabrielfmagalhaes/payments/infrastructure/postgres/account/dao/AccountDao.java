package com.gabrielfmagalhaes.payments.infrastructure.postgres.account.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gabrielfmagalhaes.payments.core.account.Account;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
public class AccountDao {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @Column(name = "credit_available", nullable = false)
    private BigDecimal creditAvailable;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static AccountDao mapToDao(final Account account) {
        return new AccountDao()
            .withId(account.getId())
            .withDocumentNumber(account.getDocumentNumber())
            .withCreditAvailable(account.getCreditAvailable())
        ;
    }

    public Account mapToEntity() {
        return new Account(id, documentNumber, creditAvailable, createdAt, updatedAt);
    }
}
