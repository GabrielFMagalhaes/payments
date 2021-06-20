package com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.converters;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.AccountResponse;

public class AccountRestConverter {

    public AccountResponse mapToRest(final Account account) {
        return new AccountResponse(
            account.getId(),
            account.getDocumentNumber(), 
            account.getCreditAvailable(),
            account.getCreatedAt(),
            account.getUpdatedAt())
        ;
    }
}
