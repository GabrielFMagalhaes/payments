package com.gabrielfmagalhaes.payments.core.account.ports.incoming;


import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAccountRequest {

    @JsonProperty("document_number")
    @NotEmpty
    private String documentNumber;
}
