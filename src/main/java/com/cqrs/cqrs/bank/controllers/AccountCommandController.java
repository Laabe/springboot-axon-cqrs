package com.cqrs.cqrs.bank.controllers;

import com.cqrs.cqrs.bank.dto.AccountCreateDTO;
import com.cqrs.cqrs.bank.dto.MoneyCreditDTO;
import com.cqrs.cqrs.bank.dto.MoneyDebitDTO;
import com.cqrs.cqrs.bank.services.commands.AccountCommandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/bank-accounts")
public class AccountCommandController {
    private final AccountCommandService accountCommandService;

    public AccountCommandController(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @PostMapping
    public CompletableFuture<String> createAccount(@RequestBody AccountCreateDTO accountCreateDTO){
        return accountCommandService.createAccount(accountCreateDTO);
    }

    @PutMapping("/credits")
    public CompletableFuture<String> creditMoneyToAccount(@RequestBody MoneyCreditDTO moneyCreditDTO) {
        return accountCommandService.creditMoneyToAccount(moneyCreditDTO);
    }

    @PutMapping("/debits")
    public CompletableFuture<String> debitMoneyFromAccount(@RequestBody MoneyDebitDTO moneyDebitDTO) {
        return accountCommandService.debitMoneyFromAccount(moneyDebitDTO);
    }
}
