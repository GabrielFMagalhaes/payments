package com.gabrielfmagalhaes.payments.core.account.ports.incoming;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateAccountRequest {

    @JsonProperty("document_number")
    @NotEmpty(message = "Document Number should not be empty")
    private String documentNumber;
}
