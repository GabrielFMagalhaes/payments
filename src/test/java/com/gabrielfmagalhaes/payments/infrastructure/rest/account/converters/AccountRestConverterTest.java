package com.gabrielfmagalhaes.payments.infrastructure.rest.account.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.converters.AccountRestConverter;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.AccountResponse;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.CreateAccountResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AccountRestConverterTest {
    
    @MockBean
    private AccountRestConverter accountRestConverter;

    private final static LocalDateTime CURRENT_DATE = LocalDateTime.now();

    private final static UUID UUID = java.util.UUID.randomUUID();

    private final static String VALID_DOCUMENT_NUMBER = "12345678900";
    
    private static final Account VALID_TRANSACTION = new Account(
        UUID, 
        VALID_DOCUMENT_NUMBER,
        CURRENT_DATE,
        CURRENT_DATE)
    ;

    @Test
    void shouldConvertEntityAccountToRest() {
        AccountResponse expected = new AccountResponse(
            UUID,
            VALID_DOCUMENT_NUMBER);

        when(accountRestConverter.mapAccountToRest(any(Account.class)))
            .thenReturn(expected);

        AccountResponse result = accountRestConverter.mapAccountToRest(VALID_TRANSACTION);

        assertThat(expected).isEqualTo(result);
    }

    

    @Test
    void shouldConvertEntityAccountToNewAccountRest() {
        CreateAccountResponse expected = new CreateAccountResponse(UUID);


        when(accountRestConverter.mapNewAccountToRest(any(Account.class)))
            .thenReturn(expected);
            
        CreateAccountResponse result = accountRestConverter
            .mapNewAccountToRest(VALID_TRANSACTION);

        assertThat(expected).isEqualTo(result);
    }
}
