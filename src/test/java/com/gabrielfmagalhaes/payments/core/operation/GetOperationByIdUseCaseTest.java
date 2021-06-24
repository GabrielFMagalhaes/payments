package com.gabrielfmagalhaes.payments.core.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.gabrielfmagalhaes.payments.core.operation.exceptions.InvalidOperationTypeException;
import com.gabrielfmagalhaes.payments.core.operation.model.Operation;
import com.gabrielfmagalhaes.payments.core.operation.ports.outgoing.OperationRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.operation.usecase.impl.GetOperationByIdUseCaseImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetOperationByIdUseCaseTest {

    @Mock
    private OperationRepositoryUseCase operationRepositoryUseCase;

    @InjectMocks
    private GetOperationByIdUseCaseImpl getOperationByIdUseCaseImpl;

    private final static Long OPERATION_ID = 1l;
    private final static String OPERATION_DESCRIPTION = "PAGAMENTO";

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldGetOperationById() {
        final Operation operation = new Operation(
            OPERATION_ID, 
            OPERATION_DESCRIPTION)
        ; 

        when(operationRepositoryUseCase.findById(OPERATION_ID)).thenReturn(Optional.of(operation));

        Operation expected = getOperationByIdUseCaseImpl.execute(OPERATION_ID);

        assertThat(expected).isNotNull();
    }

    @Test
    void shouldThrowErrorWhenGetOperationByIdWithNoExistingId() {
        final Long OPERATION_ID = 9999l;

        when(operationRepositoryUseCase.findById(OPERATION_ID)).thenReturn(Optional.empty());

        assertThrows(InvalidOperationTypeException.class,() -> {
            getOperationByIdUseCaseImpl.execute(OPERATION_ID);
        }); 

        verifyNoMoreInteractions(operationRepositoryUseCase);
    }
}
