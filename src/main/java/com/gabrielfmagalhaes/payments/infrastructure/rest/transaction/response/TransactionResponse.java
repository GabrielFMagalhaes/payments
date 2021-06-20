package com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.response;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    @JsonProperty("transaction_id")
    private UUID transactionId;
}
