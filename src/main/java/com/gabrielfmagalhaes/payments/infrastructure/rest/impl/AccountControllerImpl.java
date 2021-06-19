package com.gabrielfmagalhaes.payments.infrastructure.rest.impl;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.core.account.usecase.CreateAccountUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.rest.AccountController;
import com.gabrielfmagalhaes.payments.infrastructure.rest.converters.AccountRestConverter;
import com.gabrielfmagalhaes.payments.infrastructure.rest.response.CreateAccountResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/accounts")
@Api(value = "Accounts", description = "REST API for Accounts", tags = { "Accounts" })
public class AccountControllerImpl implements AccountController {

    @Autowired
    private CreateAccountUseCase createAccountUseCase;

    @Autowired
    private AccountRestConverter accountRestConverter;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateAccountResponse create(CreateAccountRequest request) {
        Account account = createAccountUseCase.create(request);
        
        return accountRestConverter.mapToRest(account);
    }
    
}
