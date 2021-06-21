package com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.impl;

import java.util.Optional;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.dao.AccountDao;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.AccountRepository;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryUseCase {

    private final AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(AccountDao.mapToDao(account)).mapToEntity();
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return accountRepository.findById(id).map(AccountDao::mapToEntity);
    }

    @Override
    public Optional<Account> findByDocumentNumber(String documentNumber) {
        return accountRepository.findByDocumentNumber(documentNumber).map(AccountDao::mapToEntity);
    }
}
