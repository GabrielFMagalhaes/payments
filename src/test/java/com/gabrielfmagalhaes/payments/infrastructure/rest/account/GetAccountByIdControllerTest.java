package com.gabrielfmagalhaes.payments.infrastructure.rest.account;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.usecase.CreateAccountUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.GetAccountByIdUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.PaymentsApplication;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.AccountController;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.converters.AccountRestConverter;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.AccountResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AccountController.class)
@ContextConfiguration(classes = PaymentsApplication.class)
public class GetAccountByIdControllerTest {

    @MockBean
    private GetAccountByIdUseCase getAccountByIdUseCase;
    
    @MockBean
    private CreateAccountUseCase createAccountUseCase;

    @MockBean
    private AccountRestConverter accountRestConverter;

    @Autowired
    private MockMvc mockMvc;

    private Account account;

    private final static String VALID_DOCUMENT_NUMBER = "12345678900";

    private final static String VALID_ID = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        account = new Account(VALID_DOCUMENT_NUMBER);
    }

    @Test 
    void shouldFindExistingUserById() throws Exception {
        
        AccountResponse response = new AccountResponse(
            UUID.randomUUID(), 
            VALID_DOCUMENT_NUMBER);

        when(getAccountByIdUseCase.execute(any(UUID.class))).thenReturn(account);
        when(accountRestConverter.mapAccountToRest(any(Account.class))).thenReturn(response);

        this.mockMvc.perform(get("/accounts/{accountId}", VALID_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.account_id", is(response.getId().toString())))
            .andExpect(jsonPath("$.document_number", is(response.getDocumentNumber())))
        ;
    }

    @Test 
    void shouldReturn404WhenUserIsNotFound() throws Exception {
        when(getAccountByIdUseCase.execute(any(UUID.class))).thenThrow(new AccountNotFoundException("nothing"));

        this.mockMvc.perform(get("/accounts/{accountId}", VALID_ID))
            .andExpect(status().isNotFound())
        ;
    }
}
