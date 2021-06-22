package com.gabrielfmagalhaes.payments.infrastructure.postgres.account.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.Account;

import org.junit.jupiter.api.Test;

public class AccountDaoTest {

    private final static String DOCUMENT_NUMBER = "12345678900";

    private final static LocalDateTime CURRENT_DATE = LocalDateTime.now();

    private final static UUID UUID = java.util.UUID.randomUUID();
    
    private static final Account VALID_ACCOUNT = new Account(
        UUID, 
        DOCUMENT_NUMBER, 
        CURRENT_DATE,
        CURRENT_DATE)
    ;

    private static final PostgresAccount VALID_ACCOUNT_DAO = new PostgresAccount(
        UUID, 
        DOCUMENT_NUMBER,
        CURRENT_DATE,
        CURRENT_DATE)
    ;

    @Test
    void shouldConvertAccountDaoToEntity() {
        assertThat(VALID_ACCOUNT_DAO.mapToEntity()).isEqualTo(VALID_ACCOUNT);
    }

    @Test
    void shouldConvertEntityAccountToDao() {
        PostgresAccount accountDao = PostgresAccount.mapToPostgres(VALID_ACCOUNT);
        accountDao.setCreatedAt(CURRENT_DATE);
        accountDao.setUpdatedAt(CURRENT_DATE);

        assertThat(accountDao).isEqualTo(VALID_ACCOUNT_DAO);
    }
}
