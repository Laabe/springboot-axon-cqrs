package com.cqrs.cqrs.bank.entities.repositories;

import com.cqrs.cqrs.bank.entities.AccountQueryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<AccountQueryDocument, String> {
}
