package com.cqrs.cqrs.bank.commands;

import lombok.AllArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
public class BaseCommand<T> {
    @TargetAggregateIdentifier
    public final T id;
}
