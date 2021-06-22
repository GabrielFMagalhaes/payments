package com.gabrielfmagalhaes.payments.core.operation.usecase.impl;

import javax.management.openmbean.InvalidOpenTypeException;

import com.gabrielfmagalhaes.payments.core.operation.Operation;
import com.gabrielfmagalhaes.payments.core.operation.ports.outgoing.OperationRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.operation.usecase.GetOperationByIdUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetOperationByIdUseCaseImpl implements GetOperationByIdUseCase {

    private final OperationRepositoryUseCase operationRepositoryUseCase;
    
    @Override
    public Operation execute(Long id) {
        return operationRepositoryUseCase.findById(id)
            .orElseThrow(() -> new InvalidOpenTypeException("No operation was found with id: " + id));
    }

}

