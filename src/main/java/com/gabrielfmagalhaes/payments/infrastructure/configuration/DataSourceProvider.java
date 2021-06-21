package com.gabrielfmagalhaes.payments.infrastructure.configuration;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProvider extends HikariConfig {

    @Bean
    public DataSource getDataSource() {
        return new HikariDataSource(this);
    }
}
