package com.cqrs.cqrs.bank.services.queries;

import com.cqrs.cqrs.bank.entities.AccountQueryDocument;
import com.cqrs.cqrs.bank.entities.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AccountQueryServiceImpl implements AccountQueryService {
    private final EventStore eventStore;
    private final AccountRepository accountRepository;
    @Override
    public List<Object> listEventsForAccount(String accountNumber) {
        return eventStore.readEvents(accountNumber)
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }

    @Override
    public AccountQueryDocument getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber).get();
    }
}
