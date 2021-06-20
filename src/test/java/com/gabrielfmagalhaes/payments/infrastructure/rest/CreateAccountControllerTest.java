package com.gabrielfmagalhaes.payments.infrastructure.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielfmagalhaes.payments.application.PaymentsApplication;
import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountAlreadyExistsException;
import com.gabrielfmagalhaes.payments.core.account.ports.incoming.CreateAccountRequest;
import com.gabrielfmagalhaes.payments.core.account.usecase.CreateAccountUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.GetAccountByIdUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.rest.converters.AccountRestConverter;
import com.gabrielfmagalhaes.payments.infrastructure.rest.response.AccountResponse;

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
public class CreateAccountControllerTest {

    @MockBean
    private CreateAccountUseCase createAccountUseCase;
    
    @MockBean
    private GetAccountByIdUseCase getAccountByIdUseCase;

    @MockBean
    private AccountRestConverter accountRestConverter;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    private Account account;

    private final static String VALID_DOCUMENT_NUMBER = "12345678900";
    
    private final static LocalDateTime currentDate = LocalDateTime.now();

    @BeforeEach
    void setUp() {        
        mapper = new ObjectMapper();

        account = new Account(VALID_DOCUMENT_NUMBER);
    }

    @Test 
    void shouldCreateUser() throws Exception {
        CreateAccountRequest request = new CreateAccountRequest(VALID_DOCUMENT_NUMBER);
        
        AccountResponse response = new AccountResponse(
            UUID.randomUUID(), 
            VALID_DOCUMENT_NUMBER, 
            currentDate, 
            currentDate);

        when(createAccountUseCase.execute(any(CreateAccountRequest.class))).thenReturn(account);
        when(accountRestConverter.mapToRest(any(Account.class))).thenReturn(response);

        this.mockMvc.perform(post("/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(response.getId().toString())))
            .andExpect(jsonPath("$.document_number", is(response.getDocumentNumber())))
            .andExpect(jsonPath("$.created_at").isNotEmpty())
            .andExpect(jsonPath("$.updated_at").isNotEmpty())
        ;
    }

    @Test 
    void shouldReturn400WhenCreateAccountWithoutDocumentNumber() throws Exception {
        CreateAccountRequest request = new CreateAccountRequest(null);

        this.mockMvc.perform(post("/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
        ;
    }

    @Test 
    void shouldReturn409WithExistingDocumentNumberAccount() throws Exception {
        CreateAccountRequest request = new CreateAccountRequest(VALID_DOCUMENT_NUMBER);

        when(createAccountUseCase.execute(request)).thenThrow(new AccountAlreadyExistsException());

        this.mockMvc.perform(post("/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isConflict())
        ;
    }
}
