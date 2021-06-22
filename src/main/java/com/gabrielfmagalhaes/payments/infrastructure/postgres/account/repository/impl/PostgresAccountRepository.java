package com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.impl;

import java.util.Optional;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.dao.PostgresAccount;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.PostgresSpringDataAccountRepository;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class PostgresAccountRepository implements AccountRepositoryUseCase {

    private final PostgresSpringDataAccountRepository postgresSpringDataAccountRepository;

    @Override
    public Account save(Account account) {
        return postgresSpringDataAccountRepository
            .save(PostgresAccount.mapToPostgres(account)).mapToEntity();
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return postgresSpringDataAccountRepository
            .findById(id)
            .map(PostgresAccount::mapToEntity);
    }

    @Override
    public Optional<Account> findByDocumentNumber(String documentNumber) {
        return postgresSpringDataAccountRepository
            .findByDocumentNumber(documentNumber)
            .map(PostgresAccount::mapToEntity);
    }
}
