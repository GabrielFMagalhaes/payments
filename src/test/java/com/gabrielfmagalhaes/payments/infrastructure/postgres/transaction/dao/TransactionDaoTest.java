package com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.operation.Operation;
import com.gabrielfmagalhaes.payments.core.transaction.Transaction;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.dao.PostgresAccount;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.operation.dao.PostgresOperation;

import org.junit.jupiter.api.Test;

public class TransactionDaoTest {
    
    private final static LocalDateTime CURRENT_DATE = LocalDateTime.now();

    private final static UUID TRANSACTION_UUID = java.util.UUID.randomUUID();
    private final static BigDecimal TRANSACTION_AMOUNT = new BigDecimal(30);

    private final static UUID ACCOUNT_UUID = java.util.UUID.randomUUID();
    private final static String ACCOUNT_DOCUMENT_NUMBER = "12345678900";
    
    private final static Long OPERATION_ID = 1l;
    private final static String OPERATION_DESCRIPTION = "PAGAMENTO";
    
    private static final Account VALID_ACCOUNT = new Account(
        ACCOUNT_UUID, 
        ACCOUNT_DOCUMENT_NUMBER, 
        CURRENT_DATE,
        CURRENT_DATE)
    ;

    private static final PostgresAccount VALID_ACCOUNT_DAO = new PostgresAccount(
        ACCOUNT_UUID, 
        ACCOUNT_DOCUMENT_NUMBER,
        CURRENT_DATE,
        CURRENT_DATE)
    ;

    private static final PostgresOperation VALID_OPERATION_DAO = new PostgresOperation(
        OPERATION_ID, 
        OPERATION_DESCRIPTION)
    ;

    private static final Operation VALID_OPERATION = new Operation(
        OPERATION_ID, 
        OPERATION_DESCRIPTION)
    ;

    private static final Transaction VALID_TRANSACTION = new Transaction(
        TRANSACTION_UUID, 
        VALID_ACCOUNT, 
        VALID_OPERATION,
        TRANSACTION_AMOUNT,
        CURRENT_DATE)
    ;

    private static final PostgresTransaction VALID_TRANSACTION_DAO = new PostgresTransaction(
        TRANSACTION_UUID, 
        VALID_ACCOUNT_DAO, 
        VALID_OPERATION_DAO,
        TRANSACTION_AMOUNT,
        CURRENT_DATE)
    ;

    @Test
    void shouldConvertTransactionDaoToEntity() {
        assertThat(VALID_TRANSACTION_DAO.mapToEntity()).isEqualTo(VALID_TRANSACTION);
    }

    @Test
    void shouldConvertEntityTransactionToDao() {
        PostgresTransaction transactionDao = PostgresTransaction.mapToPostgres(VALID_TRANSACTION);
        transactionDao.setEventDate(CURRENT_DATE);

        assertThat(transactionDao).isNotNull();
    }
}
