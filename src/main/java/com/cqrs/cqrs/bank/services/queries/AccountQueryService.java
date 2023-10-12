package com.cqrs.cqrs.bank.services.queries;

import com.cqrs.cqrs.bank.entities.AccountQueryDocument;

import java.util.List;

public interface AccountQueryService {
    public List<Object> listEventsForAccount(String accountNumber);
    public AccountQueryDocument getAccount(String accountNumber);
}
