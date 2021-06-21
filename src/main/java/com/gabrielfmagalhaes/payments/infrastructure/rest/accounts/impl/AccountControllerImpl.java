package com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.impl;

import java.util.UUID;

import javax.validation.Valid;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountAlreadyExistsException;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.core.account.usecase.CreateAccountUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.GetAccountByIdUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.AccountController;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.converters.AccountRestConverter;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.AccountResponse;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.CreateAccountResponse;
import com.gabrielfmagalhaes.payments.infrastructure.rest.exceptions.NotFoundException;
import com.gabrielfmagalhaes.payments.infrastructure.rest.exceptions.ResourceConflictException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/accounts")
@Api(value = "Accounts", description = "REST API for Accounts", tags = { "Accounts" })
@AllArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final CreateAccountUseCase createAccountUseCase;

    private final GetAccountByIdUseCase getAccountByIdUseCase;

    private final AccountRestConverter accountRestConverter;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateAccountResponse create(@RequestBody @Valid CreateAccountRequest request) {
        try {
            return accountRestConverter.mapNewAccountToRest(createAccountUseCase.execute(request));
        } catch (AccountAlreadyExistsException e) {
            throw new ResourceConflictException();
        }
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{accountId}")
    public AccountResponse findById(@PathVariable String accountId) {

        try {
            Account account = getAccountByIdUseCase.execute(UUID.fromString(accountId));
            return accountRestConverter.mapAccountToRest(account);
        } catch (AccountNotFoundException e) {
            throw new NotFoundException();
        }
    }

}
