package com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.converters;

import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.AccountLimitResponse;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.AccountResponse;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.CreateAccountResponse;

public class AccountRestConverter {

    public AccountResponse mapAccountToRest(final Account account) {
        return new AccountResponse(
            account.getId(),
            account.getDocumentNumber())
        ;
    }

    public CreateAccountResponse mapNewAccountToRest(final Account account) {
        return new CreateAccountResponse(
            account.getId())
        ;
    }

    public AccountLimitResponse mapNewAccountToAccountLimitRest(final Account account) {
        return new AccountLimitResponse(
            String.valueOf(account.getAvailableCreditLimit()))
        ;
    }
}
