package com.gabrielfmagalhaes.payments.application.configuration;

import com.gabrielfmagalhaes.payments.infrastructure.rest.accounts.converters.AccountRestConverter;
import com.gabrielfmagalhaes.payments.infrastructure.rest.transaction.convertes.TransactionRestConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration {

    @Bean
    public AccountRestConverter accountRestConverter() {
        return new AccountRestConverter();
    }

    @Bean
    public TransactionRestConverter transactionRestConverter() {
        return new TransactionRestConverter();
    }
}
