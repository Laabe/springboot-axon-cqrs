package com.cqrs.cqrs.bank.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("accounts")
@NoArgsConstructor
@AllArgsConstructor
public class AccountQueryDocument {
    private String id;
    private double accountBalance;
    private String currency;
    private String status;
}
