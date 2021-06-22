package com.gabrielfmagalhaes.payments.core.account;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class Account {

    private UUID id = UUID.randomUUID();
    private String documentNumber;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Account (final String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
