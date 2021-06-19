package com.gabrielfmagalhaes.payments.infrastructure.rest.converters;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.infrastructure.rest.response.CreateAccountResponse;

public class AccountRestConverter {

    public CreateAccountResponse mapToRest(final Account account) {
        return new CreateAccountResponse(account.getDocumentNumber());
    }
}
