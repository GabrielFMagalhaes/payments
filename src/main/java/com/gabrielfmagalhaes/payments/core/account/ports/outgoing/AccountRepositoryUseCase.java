package com.gabrielfmagalhaes.payments.core.account.ports.outgoing;

import java.util.Optional;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.Account;

public interface AccountRepositoryUseCase {

    Account save(Account account);

    Optional<Account> findById(UUID id);

    Optional<Account> findByDocumentNumber(String documentNumber);
}
