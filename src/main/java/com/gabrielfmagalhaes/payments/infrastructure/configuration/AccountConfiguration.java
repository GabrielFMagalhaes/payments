package com.gabrielfmagalhaes.payments.infrastructure.configuration;

import com.gabrielfmagalhaes.payments.infrastructure.rest.converters.AccountRestConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfiguration {

    @Bean
    public AccountRestConverter accountRestConverter() {
        return new AccountRestConverter();
    }
}
