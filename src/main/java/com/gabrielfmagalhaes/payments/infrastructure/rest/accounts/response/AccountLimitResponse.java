package com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountLimitResponse {
    
    @JsonProperty("available_credit_limit")
    private String availableCreditLimit;
}
