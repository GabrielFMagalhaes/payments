package com.gabrielfmagalhaes.payments.core.transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.ports.incoming.CreateTransactionRequest;
import com.gabrielfmagalhaes.payments.core.transaction.ports.outgoing.TransactionRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.impl.CreateTransactionUseCaseImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateTransactionUseCaseTest {

    @Mock
    private TransactionRepositoryUseCase transactionRepositoryUseCase;

    @Mock
    private TransactionRepositoryUseCase transactionRepositoryUseCase2;
    
    @Mock
    private AccountRepositoryUseCase accountRepositoryUseCase;

    @InjectMocks
    private CreateTransactionUseCaseImpl createTransactionUseCase;

    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now();

    private Account account;

    private final static String VALID_DOCUMENT_NUMBER = "12345678900";

    private final static int VALID_OPERATION_TYPE_ID = 1;
    
    private final static BigDecimal AMOUNT = new BigDecimal(30);
    
    @BeforeEach
    void setUp() {        
        account = new Account(VALID_DOCUMENT_NUMBER);
    }

    @Test
    void shouldSaveTransaction() {
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            VALID_OPERATION_TYPE_ID, 
            AMOUNT);

        final Account account = new Account(
            UUID.randomUUID(),
            VALID_DOCUMENT_NUMBER,
            CURRENT_DATE, 
            CURRENT_DATE);
        
        final Transaction transaction = new Transaction(
            UUID.randomUUID(),
            account.getId(), 
            VALID_OPERATION_TYPE_ID, 
            AMOUNT,
            CURRENT_DATE);

        when(accountRepositoryUseCase.findById(any(UUID.class))).thenReturn(Optional.of(account));
        when(transactionRepositoryUseCase.save(any(Transaction.class))).thenReturn(transaction);

        Transaction expected = createTransactionUseCase.execute(request);

        assertThat(createTransactionUseCase.execute(request)).isEqualTo(expected);
    }

    @Test
    void shouldThrowErrorWhenGetAccountByIdWithNoExistingId() {
       
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            VALID_OPERATION_TYPE_ID, 
            AMOUNT);

        when(accountRepositoryUseCase.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,() -> {
            createTransactionUseCase.execute(request);
        }); 

        verify(accountRepositoryUseCase, never()).save(any(Account.class));
    }
}
