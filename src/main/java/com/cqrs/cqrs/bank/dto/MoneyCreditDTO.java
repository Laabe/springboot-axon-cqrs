package com.cqrs.cqrs.bank.dto;

import lombok.Data;

@Data
public class MoneyCreditDTO {
    private String accountId;
    private double creditAmount;
    private String currency;
}
