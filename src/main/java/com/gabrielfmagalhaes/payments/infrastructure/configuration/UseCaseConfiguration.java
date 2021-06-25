package com.gabrielfmagalhaes.payments.infrastructure.configuration;

import com.gabrielfmagalhaes.payments.core.account.usecase.impl.CreateAccountUseCaseImpl;
import com.gabrielfmagalhaes.payments.core.account.usecase.impl.GetAccountByIdUseCaseImpl;
import com.gabrielfmagalhaes.payments.core.account.usecase.impl.UpdateAccountLimitUseCaseImpl;
import com.gabrielfmagalhaes.payments.core.operation.usecase.impl.GetOperationByIdUseCaseImpl;
import com.gabrielfmagalhaes.payments.core.transaction.usecase.impl.CreateTransactionUseCaseImpl;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.PostgresSpringDataAccountRepository;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.account.repository.impl.PostgresAccountRepository;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.operation.repository.PostgresSpringDataOperationRepository;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.operation.repository.impl.PostgresOperationRepository;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.repository.PostgresSpringDataTransactionRepository;
import com.gabrielfmagalhaes.payments.infrastructure.postgres.transaction.repository.impl.PostgresTransactionRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    private PostgresAccountRepository postgresAccountRepositoryImpl(
            PostgresSpringDataAccountRepository postgresSpringDataAccountRepository) {
        return new PostgresAccountRepository(postgresSpringDataAccountRepository);
    }

    private PostgresTransactionRepository postgresTransactionRepositoryImpl(
            PostgresSpringDataTransactionRepository postgresSpringDataTransactionRepository) {
        return new PostgresTransactionRepository(postgresSpringDataTransactionRepository);
    }

    private PostgresOperationRepository postgresOperationRepositoryImpl(
            PostgresSpringDataOperationRepository postgresSpringDataOperationRepository) {
        return new PostgresOperationRepository(postgresSpringDataOperationRepository);
    }

    @Bean
    public GetOperationByIdUseCaseImpl GetOperationByIdUseCase (
        PostgresSpringDataOperationRepository postgresSpringDataOperationRepository) {
        return new GetOperationByIdUseCaseImpl(
            postgresOperationRepositoryImpl(postgresSpringDataOperationRepository));
    }

    @Bean
    public GetAccountByIdUseCaseImpl getAccountByIdUseCase (
            PostgresSpringDataAccountRepository postgresSpringDataAccountRepository) {
        return new GetAccountByIdUseCaseImpl(
            postgresAccountRepositoryImpl(postgresSpringDataAccountRepository));
    }

    @Bean
    public UpdateAccountLimitUseCaseImpl updateAccountUseCase (
            PostgresSpringDataAccountRepository postgresSpringDataAccountRepository) {
        return new UpdateAccountLimitUseCaseImpl(
            postgresAccountRepositoryImpl(postgresSpringDataAccountRepository));
    }

    @Bean
    public CreateAccountUseCaseImpl createAccountUseCase (
            PostgresSpringDataAccountRepository postgresSpringDataAccountRepository) {
        return new CreateAccountUseCaseImpl(
            postgresAccountRepositoryImpl(postgresSpringDataAccountRepository));
    }

    @Bean
    public CreateTransactionUseCaseImpl createTransactionUseCase(
            PostgresSpringDataTransactionRepository postgresSpringDataTransactionRepository,
            PostgresSpringDataAccountRepository postgresSpringDataAccountRepository,
            PostgresSpringDataOperationRepository postgresSpringDataOperationRepository) {
        return new CreateTransactionUseCaseImpl(
            postgresTransactionRepositoryImpl(postgresSpringDataTransactionRepository), 
            postgresAccountRepositoryImpl(postgresSpringDataAccountRepository),
            postgresOperationRepositoryImpl(postgresSpringDataOperationRepository));
    }
}
