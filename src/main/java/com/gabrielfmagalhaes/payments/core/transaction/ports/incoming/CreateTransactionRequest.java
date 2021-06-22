package com.gabrielfmagalhaes.payments.core.transaction.ports.incoming;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTransactionRequest {
    
    @JsonProperty("account_id")
    @NotEmpty(message = "Account Id should not be empty")
    private String accountId;

    @JsonProperty("operation_type_id")
    @Min(1)
    @Max(4)
    private Long operationTypeId;

    @NotNull
    private BigDecimal amount;
}
