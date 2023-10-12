package com.cqrs.cqrs.bank.controllers;

import com.cqrs.cqrs.bank.entities.AccountQueryDocument;
import com.cqrs.cqrs.bank.services.queries.AccountQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/bank-accounts")
public class AccountQueryController {
    private final AccountQueryService accountQueryService;

    public AccountQueryController(AccountQueryService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }

    @GetMapping("/{accountNumber}")
    public AccountQueryDocument getAccount(@PathVariable(value = "accountNumber") String accountNumber){
        return accountQueryService.getAccount(accountNumber);
    }

    @GetMapping("/{accountNumber}/events")
    public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber){
        return accountQueryService.listEventsForAccount(accountNumber);
    }

}
