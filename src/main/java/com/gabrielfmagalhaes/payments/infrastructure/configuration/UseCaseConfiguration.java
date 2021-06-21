package com.gabrielfmagalhaes.payments.infrastructure.configuration;

import com.gabrielfmagalhaes.payments.core.account.usecase.impl.CreateAccountUseCaseImpl;
import com.gabrielfmagalhaes.payments.core.account.usecase.impl.GetAccountByIdUseCaseImpl;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.impl.CreateTransactionUseCaseImpl;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.AccountRepository;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.impl.AccountRepositoryImpl;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.repository.TransactionRepository;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.repository.impl.TransactionRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Bean
    public AccountRepositoryImpl accountRepositoryImpl() {
        return new AccountRepositoryImpl(accountRepository);
    }

    @Bean
    public TransactionRepositoryImpl transactionRepositoryImpl() {
        return new TransactionRepositoryImpl(transactionRepository);
    }

    @Bean
    public GetAccountByIdUseCaseImpl getAccountByIdUseCase() {
        return new GetAccountByIdUseCaseImpl(accountRepositoryImpl());
    }

    @Bean
    public CreateAccountUseCaseImpl createAccountUseCase() {
        return new CreateAccountUseCaseImpl(accountRepositoryImpl());
    }

    @Bean
    public CreateTransactionUseCaseImpl createTransactionUseCase() {
        return new CreateTransactionUseCaseImpl(transactionRepositoryImpl(), accountRepositoryImpl());
    }
}
