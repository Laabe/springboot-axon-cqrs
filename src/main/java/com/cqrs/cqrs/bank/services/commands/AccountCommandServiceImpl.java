package com.cqrs.cqrs.bank.services.commands;

import com.cqrs.cqrs.bank.commands.CreateAccountCommand;
import com.cqrs.cqrs.bank.commands.CreditMoneyCommand;
import com.cqrs.cqrs.bank.commands.DebitMoneyCommand;
import com.cqrs.cqrs.bank.dto.AccountCreateDTO;
import com.cqrs.cqrs.bank.dto.MoneyCreditDTO;
import com.cqrs.cqrs.bank.dto.MoneyDebitDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class AccountCommandServiceImpl implements AccountCommandService {
    private final CommandGateway commandGateway;
    @Override
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
        return commandGateway.send(
                new CreateAccountCommand(
                        UUID.randomUUID().toString(),
                        accountCreateDTO.getStartingBalance(),
                        accountCreateDTO.getCurrency()
                )
        );
    }

    @Override
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
        return commandGateway.send(
                new CreditMoneyCommand(
                        accountNumber,
                        moneyCreditDTO.getCreditAmount(),
                        moneyCreditDTO.getCurrency()
                )
        );
    }

    @Override
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
        return commandGateway.send(
                new DebitMoneyCommand(
                        accountNumber,
                        moneyDebitDTO.getDebitAmount(),
                        moneyDebitDTO.getCurrency()
                )
        );
    }
}
