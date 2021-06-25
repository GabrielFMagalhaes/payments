package com.gabrielfmagalhaes.payments.infrastructure.rest.account;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountLimitRequest;
import com.gabrielfmagalhaes.payments.core.account.usecase.CreateAccountUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.GetAccountByIdUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.UpdateAccountLimitUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.PaymentsApplication;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.AccountController;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.converters.AccountRestConverter;
import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.response.AccountLimitResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AccountController.class)
@ContextConfiguration(classes = PaymentsApplication.class)
public class UpdateAccountControllerTest {

    private final static BigDecimal ACCOUNT_CREDIT_AVAILABLE = new BigDecimal(30);

    @MockBean
    private GetAccountByIdUseCase getAccountByIdUseCase;

    @MockBean
    private CreateAccountUseCase createAccountUseCase;
    
    @MockBean
    private UpdateAccountLimitUseCase updateAccountLimitUseCase;

    @MockBean
    private AccountRestConverter accountRestConverter;

    @Autowired
    private MockMvc mockMvc;

    private Account account;

    private ObjectMapper mapper;

    private final static String AVAILABLE_CREDIT_LIMIT = "5000";

    private final static BigDecimal AVAILABLE_CREDIT_LIMIT_BIG_DECIMAL = new BigDecimal(5000);

    private final static String VALID_DOCUMENT_NUMBER = "12345678900";

    private final static String VALID_ID = UUID.randomUUID().toString();

    
    private final static String INVALID_ID = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        
        account = new Account(VALID_DOCUMENT_NUMBER);
    }

    @Test 
    void shouldUpdateExistingUserById() throws Exception {

        CreateAccountLimitRequest request = new CreateAccountLimitRequest(AVAILABLE_CREDIT_LIMIT_BIG_DECIMAL);

        AccountLimitResponse response = new AccountLimitResponse(
            AVAILABLE_CREDIT_LIMIT)
        ;
    
        when(updateAccountLimitUseCase.execute(any(UUID.class), any(CreateAccountLimitRequest.class))).thenReturn(account);

        when(accountRestConverter.mapNewAccountToAccountLimitRest(any(Account.class))).thenReturn(response);
            this.mockMvc.perform(patch("/accounts/limits/{accountId}", VALID_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.available_credit_limit", is(response.getAvailableCreditLimit())))
        ;
    }

    @Test 
    void shouldReturn404WhenUserIsNotFound() throws Exception {
        
        CreateAccountLimitRequest request = new CreateAccountLimitRequest(AVAILABLE_CREDIT_LIMIT_BIG_DECIMAL);


        when(updateAccountLimitUseCase.execute(any(UUID.class), any(CreateAccountLimitRequest.class)))
            .thenThrow(new AccountNotFoundException("error"));

        when(accountRestConverter.mapNewAccountToAccountLimitRest(any(Account.class)))
            .thenThrow(new AccountNotFoundException("error"));
            this.mockMvc.perform(patch("/accounts/limits/{accountId}", INVALID_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isNotFound())
        ;
    }
}

