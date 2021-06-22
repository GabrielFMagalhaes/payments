package com.gabrielfmagalhaes.payments.infrastructure.postgres.operation.repository;

import com.gabrielfmagalhaes.payments.infrastructure.postgres.operation.dao.PostgresOperation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresSpringDataOperationRepository extends JpaRepository<PostgresOperation, Long> {
    
}
