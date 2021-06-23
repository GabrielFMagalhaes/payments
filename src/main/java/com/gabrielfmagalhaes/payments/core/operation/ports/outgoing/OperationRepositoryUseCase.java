package com.gabrielfmagalhaes.payments.core.operation.ports.outgoing;

import java.util.Optional;

import com.gabrielfmagalhaes.payments.core.operation.model.Operation;

public interface OperationRepositoryUseCase {

    Optional<Operation> findById(Long id);
}
