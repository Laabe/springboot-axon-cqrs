package com.cqrs.cqrs.giftCard.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class IssueGiftCardCommand {
    @TargetAggregateIdentifier
    private String id;
    private Integer amount;
}
