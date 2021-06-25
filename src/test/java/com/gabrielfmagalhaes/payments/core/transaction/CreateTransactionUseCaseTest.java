package com.gabrielfmagalhaes.payments.core.transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.operation.exceptions.InvalidOperationTypeException;
import com.gabrielfmagalhaes.payments.core.operation.model.Operation;
import com.gabrielfmagalhaes.payments.core.operation.ports.outgoing.OperationRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.model.Transaction;
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
    private AccountRepositoryUseCase accountRepositoryUseCase;

    @Mock
    private OperationRepositoryUseCase operationRepositoryUseCase;

    @InjectMocks
    private CreateTransactionUseCaseImpl createTransactionUseCase;

    private final static LocalDateTime CURRENT_DATE = LocalDateTime.now();

    private final static BigDecimal POSITIVE_TRANSACTION_AMOUNT = new BigDecimal(30);
    private final static BigDecimal NEGATIVE_TRANSACTION_AMOUNT = new BigDecimal(-30);
    
    private final static BigDecimal ACCOUNT_CREDIT_AVAILABLE = new BigDecimal(5000);

    private final static UUID ACCOUNT_UUID = java.util.UUID.randomUUID();
    private final static String ACCOUNT_DOCUMENT_NUMBER = "12345678900";
    
    private final static Long OPERATION_ID = 1l;
    private final static String OPERATION_PAYMENT = "PAGAMENTO";
    private final static String OPERATION_WITHDRAW = "COMPRA";

    private Account account;
    private Operation operation;

    @BeforeEach
    void setUp() { 
         account = new Account(
            ACCOUNT_UUID, 
            ACCOUNT_DOCUMENT_NUMBER, 
            ACCOUNT_CREDIT_AVAILABLE,
            CURRENT_DATE,
            CURRENT_DATE)
        ;
        
        operation = new Operation(
            OPERATION_ID, 
            OPERATION_PAYMENT)
        ;       
    }

    @Test
    void shouldSaveTransactionWhenPaymentOperation() {
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            OPERATION_ID, 
            POSITIVE_TRANSACTION_AMOUNT);

        when(accountRepositoryUseCase.findById(account.getId())).thenReturn(Optional.of(account));
        when(operationRepositoryUseCase.findById(OPERATION_ID)).thenReturn(Optional.of(operation));

        Transaction expected = createTransactionUseCase.execute(request);

        assertThat(createTransactionUseCase.execute(request)).isEqualTo(expected);
    }

    @Test
    void shouldSaveTransactionWhenWithdrawOperation() {
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            OPERATION_ID, 
            NEGATIVE_TRANSACTION_AMOUNT);

        operation = new Operation(
            OPERATION_ID, 
            OPERATION_WITHDRAW)
        ;      

        when(accountRepositoryUseCase.findById(account.getId())).thenReturn(Optional.of(account));
        when(operationRepositoryUseCase.findById(OPERATION_ID)).thenReturn(Optional.of(operation));

        Transaction expected = createTransactionUseCase.execute(request);

        assertThat(createTransactionUseCase.execute(request)).isEqualTo(expected);
    }

    @Test
    void shouldThrowErrorWhenGetAccountByIdWithNoExistingId() {
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            OPERATION_ID, 
            POSITIVE_TRANSACTION_AMOUNT);

        when(accountRepositoryUseCase.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,() -> {
            createTransactionUseCase.execute(request);
        }); 

        verifyNoMoreInteractions(accountRepositoryUseCase);
        verifyNoMoreInteractions(transactionRepositoryUseCase);
    }

    @Test
    void shouldThrowErrorWhenGetOperationByIdWithNoExistingId() {
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            OPERATION_ID, 
            POSITIVE_TRANSACTION_AMOUNT);

        when(accountRepositoryUseCase.findById(any(UUID.class))).thenReturn(Optional.of(account));
        when(operationRepositoryUseCase.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(InvalidOperationTypeException.class,() -> {
            createTransactionUseCase.execute(request);
        }); 

        verifyNoMoreInteractions(accountRepositoryUseCase);
        verifyNoMoreInteractions(operationRepositoryUseCase);
        verifyNoMoreInteractions(transactionRepositoryUseCase);
    }

    @Test
    void shouldThrowErrorWhenInvalidAmountForPaymentOperation() {
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            OPERATION_ID, 
            NEGATIVE_TRANSACTION_AMOUNT);

        when(accountRepositoryUseCase.findById(any(UUID.class))).thenReturn(Optional.of(account));
        when(operationRepositoryUseCase.findById(any(Long.class))).thenReturn(Optional.of(operation));

        assertThrows(InvalidOperationTypeException.class,() -> {
            createTransactionUseCase.execute(request);
        }); 

        verifyNoMoreInteractions(accountRepositoryUseCase);
        verifyNoMoreInteractions(operationRepositoryUseCase);
        verifyNoMoreInteractions(transactionRepositoryUseCase);
    }

    @Test
    void shouldThrowErrorWhenInvalidAmountForWithdrawOperation() {
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            OPERATION_ID, 
            POSITIVE_TRANSACTION_AMOUNT);
            
        operation = new Operation(
            OPERATION_ID, 
            OPERATION_WITHDRAW)
        ;  

        when(accountRepositoryUseCase.findById(any(UUID.class))).thenReturn(Optional.of(account));
        when(operationRepositoryUseCase.findById(any(Long.class))).thenReturn(Optional.of(operation));

        assertThrows(InvalidOperationTypeException.class,() -> {
            createTransactionUseCase.execute(request);
        }); 

        verifyNoMoreInteractions(accountRepositoryUseCase);
        verifyNoMoreInteractions(operationRepositoryUseCase);
        verifyNoMoreInteractions(transactionRepositoryUseCase);
    }

    
}
