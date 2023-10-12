package com.cqrs.cqrs.bank.entities.handlers;

import com.cqrs.cqrs.bank.aggregates.AccountAggregate;
import com.cqrs.cqrs.bank.entities.AccountQueryDocument;
import com.cqrs.cqrs.bank.entities.repositories.AccountRepository;
import com.cqrs.cqrs.bank.events.BaseEvent;
import lombok.AllArgsConstructor;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AccountQueryDocumentManager {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    @Qualifier("accountAggregateEventSourcingRepository")
    private EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository;

    @EventSourcingHandler
    void on(BaseEvent event){
        persistAccount(buildQueryAccount(getAccountFromEvent(event)));
    }


    private AccountAggregate getAccountFromEvent(BaseEvent event){
        return accountAggregateEventSourcingRepository.load(event.id).getWrappedAggregate().getAggregateRoot();
    }

    private AccountQueryDocument findExistingOrCreateQueryAccount(String id){
        return accountRepository.findById(id).isPresent() ? accountRepository.findById(id).get() : new AccountQueryDocument();
    }

    private AccountQueryDocument buildQueryAccount(AccountAggregate accountAggregate){
        AccountQueryDocument accountQueryDocument = findExistingOrCreateQueryAccount(accountAggregate.getId());

        accountQueryDocument.setId(accountAggregate.getId());
        accountQueryDocument.setAccountBalance(accountAggregate.getAccountBalance());
        accountQueryDocument.setCurrency(accountAggregate.getCurrency());
        accountQueryDocument.setStatus(accountAggregate.getStatus());

        return accountQueryDocument;
    }

    private void persistAccount(AccountQueryDocument accountQueryDocument){
        accountRepository.save(accountQueryDocument);
    }

}
