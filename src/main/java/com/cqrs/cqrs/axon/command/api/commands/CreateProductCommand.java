package com.cqrs.cqrs.axon.command.api.commands;

import lombok.Data;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
