package com.gabrielfmagalhaes.payments.infrastructure.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountResponse {
    @JsonProperty("document_number")
    private String documentNumber;
}
