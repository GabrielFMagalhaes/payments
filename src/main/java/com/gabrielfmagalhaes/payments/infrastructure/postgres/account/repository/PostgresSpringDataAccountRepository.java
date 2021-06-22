package com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository;

import java.util.Optional;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.dao.PostgresAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresSpringDataAccountRepository extends JpaRepository<PostgresAccount, UUID> {
    
    Optional<PostgresAccount> findByDocumentNumber(String documentNumber);
}
