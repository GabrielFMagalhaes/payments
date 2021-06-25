package com.gabrielfmagalhaes.payments.core.account.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.model.Account;
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

    private final static BigDecimal ACCOUNT_CREDIT_AVAILABLE = new BigDecimal(5000);

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
            ACCOUNT_CREDIT_AVAILABLE,
            CURRENT_DATE, CURRENT_DATE)
        ;

        when(accountRepositoryUseCase.findById(uuid)).thenReturn(Optional.of(account));

        Account expected = getAccountByIdUseCase.execute(uuid);

        assertThat(expected).isNotNull();

        verify(accountRepositoryUseCase).findById(any(UUID.class));
    }

    @Test
    void shouldThrowErrorWhenGetAccountByIdWithNoExistingId() {

        UUID uuid = UUID.randomUUID();

        when(accountRepositoryUseCase.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,() -> {
            getAccountByIdUseCase.execute(uuid);
        }); 
        
        verifyNoMoreInteractions(accountRepositoryUseCase);
    }
}
