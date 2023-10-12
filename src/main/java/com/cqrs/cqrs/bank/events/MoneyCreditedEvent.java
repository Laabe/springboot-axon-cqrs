package com.cqrs.cqrs.bank.events;

public class MoneyCreditedEvent extends BaseEvent<String> {
    public final double creditBalance;
    public final String currency;

    public MoneyCreditedEvent(String id, double creditBalance, String currency) {
        super(id);
        this.creditBalance = creditBalance;
        this.currency = currency;
    }
}
