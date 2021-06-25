package com.gabrielfmagalhaes.payments.core.account.ports.incoming;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateAccountLimitRequest {
    
    @NotNull
    @JsonProperty("available_credit_limit")
    private BigDecimal availableCreditLimit;
}
