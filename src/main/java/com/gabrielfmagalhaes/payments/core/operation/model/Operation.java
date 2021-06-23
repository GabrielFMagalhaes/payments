package com.gabrielfmagalhaes.payments.core.operation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class Operation {
    
    private Long id;
    private String description;
}
