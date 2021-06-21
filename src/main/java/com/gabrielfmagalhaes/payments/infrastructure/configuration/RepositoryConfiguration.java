package com.gabrielfmagalhaes.payments.infrastructure.configuration;

import com.gabrielfmagalhaes.payments.core.account.usecase.GetAccountByIdUseCase;
import com.gabrielfmagalhaes.payments.core.account.usecase.impl.CreateAccountUseCaseImpl;
import com.gabrielfmagalhaes.payments.core.account.usecase.impl.GetAccountByIdUseCaseImpl;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.CreateTransactionUseCase;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.impl.CreateTransactionUseCaseImpl;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.AccountRepository;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.impl.AccountRepositoryImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
    
    public AccountRepositoryImpl accountRepositoryImpl(AccountRepository accountRepository) {
        return new AccountRepositoryImpl(accountRepository);
    }

    @Bean
    public GetAccountByIdUseCase getAccountByIdUseCase() {
        return new GetAccountByIdUseCaseImpl();
    }

    @Bean
    public CreateAccountUseCaseImpl createAccountUseCase(AccountRepository accountRepository) {
        return new CreateAccountUseCaseImpl(accountRepositoryImpl(accountRepository));
    }

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(GetAccountByIdUseCase getAccountByIdUseCase) {
        return new CreateTransactionUseCaseImpl(getAccountByIdUseCase);
    }
}
