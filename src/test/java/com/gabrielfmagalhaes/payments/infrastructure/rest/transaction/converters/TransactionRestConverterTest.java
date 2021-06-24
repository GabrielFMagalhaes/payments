package com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.core.operation.model.Operation;
import com.gabrielfmagalhaes.payments.core.transaction.model.Transaction;
import com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.convertes.TransactionRestConverter;
import com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.response.TransactionResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TransactionRestConverterTest {

    @MockBean
    private TransactionRestConverter transactionRestConverter;

    private final static LocalDateTime CURRENT_DATE = LocalDateTime.now();

    private final static UUID UUID = java.util.UUID.randomUUID();

    private final static BigDecimal TRANSACTION_AMOUNT = new BigDecimal(30);
    
    private static final Transaction VALID_TRANSACTION = new Transaction(
        UUID, 
        new Account(), 
        new Operation(),
        TRANSACTION_AMOUNT,
        CURRENT_DATE)
    ;

    @Test
    void shouldConvertEntityTransactionToRest() {
        TransactionResponse expected = new TransactionResponse(UUID);
        
        when(transactionRestConverter.mapToRest(any(Transaction.class)))
            .thenReturn(expected);

        TransactionResponse result = transactionRestConverter.mapToRest(VALID_TRANSACTION);

        assertThat(expected).isEqualTo(result);
    }
}
