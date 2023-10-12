package com.cqrs.cqrs.bank.dto;

import lombok.Data;

@Data
public class AccountCreateDTO {
    private double startingBalance;
    private String currency;
}
