package com.gabrielfmagalhaes.payments.core.operation.usecase;

import com.gabrielfmagalhaes.payments.core.operation.Operation;

public interface GetOperationByIdUseCase {
    
    Operation execute(Long id);
}
