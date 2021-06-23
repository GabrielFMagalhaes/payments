package com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.impl;

import javax.validation.Valid;

import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.operation.exceptions.InvalidOperationTypeException;
import com.gabrielfmagalhaes.payments.core.transaction.ports.incoming.CreateTransactionRequest;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.CreateTransactionUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.rest.exceptions.NotFoundException;
import com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.TransactionController;
import com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.convertes.TransactionRestConverter;
import com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.response.TransactionResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transactions")
@Api(value = "Transactions", description = "REST API for Transactions", tags = { "Transactions" })
@AllArgsConstructor
public class TransactionControllerImpl implements TransactionController {

    private final static Logger logger = LoggerFactory.getLogger(TransactionControllerImpl.class);

    private final TransactionRestConverter transactionRestConverter;

    private final CreateTransactionUseCase createTransactionUseCase;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TransactionResponse create(@RequestBody @Valid CreateTransactionRequest request) {
        try {
            logger.info("Create transaction request: " + request);
            return transactionRestConverter.mapToRest(createTransactionUseCase.execute(request));
        } catch (AccountNotFoundException | InvalidOperationTypeException e) {
            logger.warn("Error for account not found or invalid operation exception. Details: ", e);
            throw new NotFoundException(e.getMessage());
        }
    }
    
}
