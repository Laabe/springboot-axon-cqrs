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

    @PutMapping(value = "/credits/{accountNumber}")
    public CompletableFuture<String> creditMoneyToAccount(
            @PathVariable(value = "accountNumber") String accountNumber,
            @RequestBody MoneyCreditDTO moneyCreditDTO
    ) {
        return accountCommandService.creditMoneyToAccount(accountNumber, moneyCreditDTO);
    }

    @PutMapping(value = "/debits/{accountNumber}")
    public CompletableFuture<String> debitMoneyFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber,
            @RequestBody MoneyDebitDTO moneyDebitDTO
    ) {
        return accountCommandService.debitMoneyFromAccount(accountNumber, moneyDebitDTO);
    }
}
