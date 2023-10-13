package com.cqrs.cqrs.bank.services.commands;

import com.cqrs.cqrs.bank.dto.AccountCreateDTO;
import com.cqrs.cqrs.bank.dto.MoneyCreditDTO;
import com.cqrs.cqrs.bank.dto.MoneyDebitDTO;

import java.util.concurrent.CompletableFuture;

public interface AccountCommandService {
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
    public CompletableFuture<String> creditMoneyToAccount(MoneyCreditDTO moneyCreditDTO);
    public CompletableFuture<String> debitMoneyFromAccount(MoneyDebitDTO moneyDebitDTO);
}
