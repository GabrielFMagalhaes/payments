package com.gabrielfmagalhaes.payments.infrastructure.configuration;

import com.gabrielfmagalhaes.payments.core.account.usecase.GetAccountByIdUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.impl.CreateAccountUseCaseImpl;
import com.gabrielfmagalhaes.payments.core.account.usecase.impl.GetAccountByIdUseCaseImpl;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.CreateTransactionUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.impl.CreateTransactionUseCaseImpl;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.AccountRepository;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.impl.AccountRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
    
    @Autowired
    private AccountRepository accountRepository;

    @Bean
    public AccountRepositoryImpl accountRepositoryImpl() {
        return new AccountRepositoryImpl(accountRepository);
    }

    @Bean
    public GetAccountByIdUseCase getAccountByIdUseCase() {
        return new GetAccountByIdUseCaseImpl(accountRepositoryImpl());
    }

    @Bean
    public CreateAccountUseCaseImpl createAccountUseCase() {
        return new CreateAccountUseCaseImpl(accountRepositoryImpl());
    }

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(GetAccountByIdUseCase getAccountByIdUseCase) {
        return new CreateTransactionUseCaseImpl(getAccountByIdUseCase);
    }
}
