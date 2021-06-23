package com.gabrielfmagalhaes.payments.core.operation.usecase.impl;

import javax.management.openmbean.InvalidOpenTypeException;

import com.gabrielfmagalhaes.payments.core.operation.model.Operation;
import com.gabrielfmagalhaes.payments.core.operation.ports.outgoing.OperationRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.operation.usecase.GetOperationByIdUseCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetOperationByIdUseCaseImpl implements GetOperationByIdUseCase {

    private final static Logger logger = LoggerFactory.getLogger(GetOperationByIdUseCaseImpl.class);

    private final OperationRepositoryUseCase operationRepositoryUseCase;
    
    @Override
    public Operation execute(Long id) {
        logger.info("Finding operation by id from database: " + id);

        return operationRepositoryUseCase.findById(id)
            .orElseThrow(() -> new InvalidOpenTypeException("No operation was found with id: " + id));
    }

}

