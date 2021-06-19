package com.gabrielfmagalhaes.payments.infrastructure.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielfmagalhaes.payments.application.PaymentsApplication;
import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.core.account.usecase.CreateAccountUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.rest.converters.AccountRestConverter;
import com.gabrielfmagalhaes.payments.infrastructure.rest.impl.AccountControllerImpl;
import com.gabrielfmagalhaes.payments.infrastructure.rest.response.CreateAccountResponse;

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
public class AccountControllerTest {
    
    private AccountController controller;

    @MockBean
    private CreateAccountUseCase createAccountUseCase;

    @MockBean
    private AccountRestConverter accountRestConverter;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    private Account account;

    private final static String VALID_DOCUMENT_NUMBER = "12345678900";

    @BeforeEach
    void setUp() {
        controller = new AccountControllerImpl();
        
        mapper = new ObjectMapper();

        account = new Account(VALID_DOCUMENT_NUMBER);
    }

    @Test 
    void shouldCreateUser() throws Exception {
        CreateAccountRequest request = new CreateAccountRequest(VALID_DOCUMENT_NUMBER);
        CreateAccountResponse response = new CreateAccountResponse(VALID_DOCUMENT_NUMBER);

        when(createAccountUseCase.create(any(CreateAccountRequest.class))).thenReturn(account);
        when(accountRestConverter.mapToRest(any(Account.class))).thenReturn(response);

        this.mockMvc.perform(post("/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.document_number", is(request.getDocumentNumber())))
        ;
    }
}
