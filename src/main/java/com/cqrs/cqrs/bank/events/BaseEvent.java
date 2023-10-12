package com.cqrs.cqrs.bank.events;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseEvent<T> {
    public String id;
}
