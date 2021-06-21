package com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.repository;

import java.util.UUID;

import com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.dao.TransactionDao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionDao, UUID> {
    
}
