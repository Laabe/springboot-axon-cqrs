package com.cqrs.cqrs.bank.dto;

import lombok.Data;

@Data
public class MoneyDebitDTO {
    private String accountId;
    private double debitAmount;
    private String currency;
}
