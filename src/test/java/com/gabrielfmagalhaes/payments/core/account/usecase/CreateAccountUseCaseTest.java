package com.gabrielfmagalhaes.payments.core.account.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountAlreadyExistsException;
import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.impl.CreateAccountUseCaseImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateAccountUseCaseTest {

    @Mock
    private AccountRepositoryUseCase accountRepositoryUseCase;

    @InjectMocks
    private CreateAccountUseCaseImpl createAccountUseCase;

    private static final String VALID_DOCUMENT_NUMBER = "12345678900";

    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        
    }

    @Test
    void shouldSaveAccount() {
       
        final CreateAccountRequest request = new CreateAccountRequest(VALID_DOCUMENT_NUMBER);
        
        final Account account = new Account(
            UUID.randomUUID(),
            VALID_DOCUMENT_NUMBER, 
            CURRENT_DATE, 
            CURRENT_DATE);

        when(accountRepositoryUseCase.findByDocumentNumber(VALID_DOCUMENT_NUMBER)).thenReturn(Optional.empty());
        when(accountRepositoryUseCase.save(any(Account.class))).thenReturn(account);

        Account expected = createAccountUseCase.execute(request);

        assertThat(createAccountUseCase.execute(request)).isEqualTo(expected);
    }

    @Test
    void shouldThrowErrorWhenSaveAccountWithExistingDocumentNumber() {
       
        final CreateAccountRequest request = new CreateAccountRequest(VALID_DOCUMENT_NUMBER);
        
        final Account account = new Account(
            UUID.randomUUID(),
            VALID_DOCUMENT_NUMBER, 
            CURRENT_DATE, 
            CURRENT_DATE);

        when(accountRepositoryUseCase.findByDocumentNumber(VALID_DOCUMENT_NUMBER)).thenReturn(Optional.of(account));

        assertThrows(AccountAlreadyExistsException.class,() -> {
            createAccountUseCase.execute(request);
        }); 

        verifyNoMoreInteractions(accountRepositoryUseCase);
    }
}
