package com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository;

import java.util.Optional;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.dao.AccountDao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDao, UUID> {
    
    Optional<AccountDao> findByDocumentNumber(String documentNumber);
}
