package com.gabrielfmagalhaes.payments.infrastructure.rest.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private UUID id;

    @JsonProperty("document_number")
    private String documentNumber;
    
    @JsonProperty("credit_available")
    private BigDecimal creditAvailable;
    
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
