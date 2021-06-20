package com.gabrielfmagalhaes.payments.infrastructure.rest.converters;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.infrastructure.rest.response.AccountResponse;

public class AccountRestConverter {

    public AccountResponse mapToRest(final Account account) {
        return new AccountResponse(
            account.getId(),
            account.getDocumentNumber(), 
            account.getCreatedAt(),
            account.getUpdatedAt())
        ;
    }
}
