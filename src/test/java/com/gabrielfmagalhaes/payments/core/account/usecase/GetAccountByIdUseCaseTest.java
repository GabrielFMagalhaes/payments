package com.gabrielfmagalhaes.payments.core.account.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.impl.GetAccountByIdUseCaseImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetAccountByIdUseCaseTest {

    @Mock
    private AccountRepositoryUseCase accountRepositoryUseCase;

    @InjectMocks
    private GetAccountByIdUseCaseImpl getAccountByIdUseCase;

    private static final String VALID_DOCUMENT_NUMBER = "12345678900";

    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now();

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldGetAccountById() {
        UUID uuid = UUID.randomUUID();

        final Account account = new Account(
            uuid, 
            VALID_DOCUMENT_NUMBER, 
            CURRENT_DATE, CURRENT_DATE)
        ;

        when(accountRepositoryUseCase.findById(uuid)).thenReturn(Optional.of(account));

        Account expected = getAccountByIdUseCase.execute(uuid);

        assertThat(expected).isNotNull();
    }

    @Test
    void shouldThrowErrorWhenGetAccountByIdWithNoExistingId() {

        UUID uuid = UUID.randomUUID();

        when(accountRepositoryUseCase.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,() -> {
            getAccountByIdUseCase.execute(uuid);
        }); 

        verify(accountRepositoryUseCase, never()).save(any(Account.class));
    }
}
