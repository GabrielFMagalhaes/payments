package com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.transaction.Transaction;

import org.junit.jupiter.api.Test;

public class TransactionDaoTest {

    private final static int OPERATION_TYPE = 1;

    private final static LocalDateTime CURRENT_DATE = LocalDateTime.now();

    private final static BigDecimal AMOUNT = new BigDecimal(30);

    private final static UUID TRANSACTION_UUID = java.util.UUID.randomUUID();
    private final static UUID ACCOUNT_UUID = java.util.UUID.randomUUID();
    
    private static final Transaction VALID_TRANSACTION = new Transaction(
        TRANSACTION_UUID, 
        ACCOUNT_UUID, 
        OPERATION_TYPE,
        AMOUNT,
        CURRENT_DATE)
    ;

    private static final TransactionDao VALID_TRANSACTION_DAO = new TransactionDao(
        TRANSACTION_UUID, 
        ACCOUNT_UUID,
        OPERATION_TYPE,
        AMOUNT,
        CURRENT_DATE)
    ;

    @Test
    void shouldConvertTransactionDaoToEntity() {
        assertThat(VALID_TRANSACTION_DAO.mapToEntity()).isEqualTo(VALID_TRANSACTION);
    }

    @Test
    void shouldConvertEntityTransactionToDao() {
        TransactionDao transactionDao = TransactionDao.mapToDao(VALID_TRANSACTION);
        transactionDao.setEventDate(CURRENT_DATE);

        assertThat(transactionDao).isEqualTo(VALID_TRANSACTION_DAO);
    }
}
