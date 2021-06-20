package com.gabrielfmagalhaes.payments.core.account;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private UUID id = UUID.randomUUID();
    private String documentNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Account (String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
