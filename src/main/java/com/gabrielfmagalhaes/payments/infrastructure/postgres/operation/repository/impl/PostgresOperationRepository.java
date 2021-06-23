package com.gabrielfmagalhaes.payments.infrastructure.postgres.operation.repository.impl;

import java.util.Optional;

import com.gabrielfmagalhaes.payments.core.operation.model.Operation;
import com.gabrielfmagalhaes.payments.core.operation.ports.outgoing.OperationRepositoryUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.operation.dao.PostgresOperation;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.operation.repository.PostgresSpringDataOperationRepository;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class PostgresOperationRepository implements OperationRepositoryUseCase {

    private final PostgresSpringDataOperationRepository postgresSpringDataOperationRepository;


    @Override
    public Optional<Operation> findById(Long id) {
        return postgresSpringDataOperationRepository
            .findById(id)
            .map(PostgresOperation::mapToEntity);
    }
}
