package com.gabrielfmagalhaes.payments.infrastructure.postgres.operation.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gabrielfmagalhaes.payments.core.operation.Operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "operation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class PostgresOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    public static PostgresOperation mapToPostgres(final Operation operation) {
        return new PostgresOperation()
            .withId(operation.getId())
            .withDescription(operation.getDescription())
        ;
    }

    public Operation mapToEntity() {
        return new Operation()
            .withId(id)
            .withDescription(description)
        ;
    }
}
