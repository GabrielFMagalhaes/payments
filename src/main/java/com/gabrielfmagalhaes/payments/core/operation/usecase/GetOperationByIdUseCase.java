package com.gabrielfmagalhaes.payments.core.operation.usecase;

import com.gabrielfmagalhaes.payments.core.operation.model.Operation;

public interface GetOperationByIdUseCase {
    
    Operation execute(Long id);
}
