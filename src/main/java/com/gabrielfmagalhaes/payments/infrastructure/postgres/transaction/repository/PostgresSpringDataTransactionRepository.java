package com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.repository;

import java.util.UUID;

import com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.dao.PostgresTransaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresSpringDataTransactionRepository extends JpaRepository<PostgresTransaction, UUID> {
    
}
