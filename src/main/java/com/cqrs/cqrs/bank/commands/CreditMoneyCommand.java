package com.cqrs.cqrs.bank.commands;

public class CreditMoneyCommand extends BaseCommand<String> {
    public final double creditAmount;
    public final String currency;

    public CreditMoneyCommand(String accountId, double creditAmount, String currency) {
        super(accountId);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}
