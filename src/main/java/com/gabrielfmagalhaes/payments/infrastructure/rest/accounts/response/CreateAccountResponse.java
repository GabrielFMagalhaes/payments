package com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountResponse {
    
    @JsonProperty("account_id")
    private UUID id;
}
