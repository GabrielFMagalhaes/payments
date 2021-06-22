package com.gabrielfmagalhaes.payments.infrastructure.rest.transaction;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielfmagalhaes.payments.core.account.Account;
import com.gabrielfmagalhaes.payments.core.transaction.Transaction;
import com.gabrielfmagalhaes.payments.core.transaction.ports.incoming.CreateTransactionRequest;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.CreateTransactionUseCase;
import com.gabrielfmagalhaes.payments.infrastructure.PaymentsApplication;
import com.gabrielfmagalhaes.payments.infrastructure.rest.exceptions.NotFoundException;
import com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.convertes.TransactionRestConverter;
import com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.response.TransactionResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TransactionController.class)
@ContextConfiguration(classes = PaymentsApplication.class)
public class CreateTransactionControllerTest {

    @MockBean
    private CreateTransactionUseCase createTransactionUseCase;
    
    @MockBean
    private TransactionRestConverter transactionRestConverter;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    private Account account;

    private final static BigDecimal TRANSACTION_AMOUNT = new BigDecimal(30);

    private final static String ACCOUNT_DOCUMENT_NUMBER = "12345678900";

    private final static Long OPERATION_ID = 1l;
    
    @BeforeEach
    void setUp() {        
        mapper = new ObjectMapper();

        account = new Account(ACCOUNT_DOCUMENT_NUMBER);
    }

    @Test
    void shouldCreateTransaction() throws Exception {
        final Transaction transaction = new Transaction();

        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            OPERATION_ID, 
            TRANSACTION_AMOUNT);

        TransactionResponse response = new TransactionResponse(
            UUID.randomUUID());

        when(createTransactionUseCase.execute(any(CreateTransactionRequest.class)))
            .thenReturn(transaction);

        when(transactionRestConverter.mapToRest(any(Transaction.class)))
            .thenReturn(response);

        this.mockMvc.perform(post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.transaction_id", is(response.getTransactionId().toString())))
        ;
    }

    @Test 
    void shouldReturn400WhenCreateTransactionWithoutAccountId() throws Exception {
        CreateTransactionRequest request = new CreateTransactionRequest(
            null, 
            OPERATION_ID, 
            TRANSACTION_AMOUNT);

        this.mockMvc.perform(post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
        ;
    }

    @Test 
    void shouldReturn400WhenCreateTransactionWithOperationTypeIdOutOfRange() throws Exception {
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            6l, 
            TRANSACTION_AMOUNT);

        this.mockMvc.perform(post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
        ;
    }

    @Test 
    void shouldReturn400WhenCreateTransactionWithoutAmount() throws Exception {
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            4l, 
            null);

        this.mockMvc.perform(post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
        ;
    }

    @Test 
    void shouldReturn404WhenUserIsNotFound() throws Exception {
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            OPERATION_ID, 
            TRANSACTION_AMOUNT);

        when(createTransactionUseCase.execute(any(CreateTransactionRequest.class)))
            .thenThrow(new NotFoundException("error"));

        this.mockMvc.perform(post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isNotFound())
        ;
    }

    @Test 
    void shouldReturn404WhenOperationIsNotFound() throws Exception {
        CreateTransactionRequest request = new CreateTransactionRequest(
            account.getId().toString(), 
            OPERATION_ID, 
            TRANSACTION_AMOUNT);

        when(createTransactionUseCase.execute(any(CreateTransactionRequest.class)))
            .thenThrow(new NotFoundException("error"));

        this.mockMvc.perform(post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request)))
            .andExpect(status().isNotFound())
        ;
    }
}
