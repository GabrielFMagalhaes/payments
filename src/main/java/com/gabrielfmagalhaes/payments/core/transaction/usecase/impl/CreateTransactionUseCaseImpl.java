package com.gabrielfmagalhaes.payments.core.transaction.usecase.impl;

import java.math.BigDecimal;
import java.util.UUID;

import com.gabrielfmagalhaes.payments.core.account.exceptions.AccountNotFoundException;
import com.gabrielfmagalhaes.payments.core.account.model.Account;
import com.gabrielfmagalhaes.payments.core.account.ports.outgoing.AccountRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.operation.exceptions.InvalidOperationTypeException;
import com.gabrielfmagalhaes.payments.core.operation.model.Operation;
import com.gabrielfmagalhaes.payments.core.operation.ports.outgoing.OperationRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.model.Transaction;
import com.gabrielfmagalhaes.payments.core.transaction.ports.incoming.CreateTransactionRequest;
import com.gabrielfmagalhaes.payments.core.transaction.ports.outgoing.TransactionRepositoryUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.CreateTransactionUseCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final static Logger logger = LoggerFactory.getLogger(CreateTransactionUseCaseImpl.class);

    private final TransactionRepositoryUseCase transactionRepositoryUseCase;
    
    private final AccountRepositoryUseCase accountRepositoryUseCase;

    private final OperationRepositoryUseCase operationRepositoryUseCase;

    @Override
    public Transaction execute(CreateTransactionRequest request) {
        UUID accountUUID = UUID.fromString(request.getAccountId());
        Long operationId = request.getOperationTypeId();


        Account account = accountRepositoryUseCase.findById(accountUUID)
            .orElseThrow(() -> new AccountNotFoundException("No account was found with id: " + accountUUID));
        
        BigDecimal accountAvailableCreditLimit = account.getAvailableCreditLimit();

        Operation operation = operationRepositoryUseCase.findById(operationId)
            .orElseThrow(() -> new InvalidOperationTypeException("No operation was found with id: " + operationId));    

        if (isValidTransaction(accountAvailableCreditLimit, operation.getDescription(), request.getAmount())) {
            
            logger.info("Persisting transaction into database: " + request);

            if(isPayment(operation.getDescription())) {
                account.setAvailableCreditLimit(accountAvailableCreditLimit.add(request.getAmount()));
            } else {
                account.setAvailableCreditLimit(accountAvailableCreditLimit.subtract(request.getAmount().abs()));
            }

            accountRepositoryUseCase.save(account);

            return transactionRepositoryUseCase.save(
                new Transaction(
                    account, 
                    operation, 
                    request.getAmount()))
            ;
        } 
        
        throw new InvalidOperationTypeException("Invalid transaction. Check if you're sending correct amount and operation type");
    }

    private boolean isPayment(String operationDescription) {
        return operationDescription.toUpperCase().contains("PAGAMENTO");
    }

    private boolean isPositiveAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean isCreditAvailable(BigDecimal accountCreditAvailable, BigDecimal transactionAmount) {
        return accountCreditAvailable.compareTo(transactionAmount.abs()) >= 0;
    }

    private boolean isValidTransaction(BigDecimal accountCreditAvailable, String operationDescription, BigDecimal amount) {
        if (isPayment(operationDescription) && isPositiveAmount(amount)) {
            return true;
        } else if (!isPayment(operationDescription) 
            && !isPositiveAmount(amount) 
            && isCreditAvailable(accountCreditAvailable, amount)) {
            return true;
        }

        return false;
    }
}
