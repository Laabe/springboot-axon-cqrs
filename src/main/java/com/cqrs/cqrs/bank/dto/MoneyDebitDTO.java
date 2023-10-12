package com.cqrs.cqrs.bank.dto;

import lombok.Data;

@Data
public class MoneyDebitDTO {
    private double debitAmount;
    private String currency;
}
