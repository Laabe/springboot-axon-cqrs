package com.cqrs.cqrs.giftCard.model;

import com.cqrs.cqrs.giftCard.command.IssueGiftCardCommand;
import com.cqrs.cqrs.giftCard.command.RedeemGiftCardCommand;
import com.cqrs.cqrs.giftCard.event.GiftCardWasIssued;
import com.cqrs.cqrs.giftCard.event.GiftCardWasRedeemed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;


@AggregateRoot
@NoArgsConstructor
public class GiftCard {
    @AggregateIdentifier
    private String id;
    private int remainingValue;

    @CommandHandler
    public GiftCard(IssueGiftCardCommand issueGiftCardCommand) {
        if (issueGiftCardCommand.getAmount() <= 0) throw new IllegalArgumentException("amount is negative");
        AggregateLifecycle.apply(
                new GiftCardWasIssued(
                        issueGiftCardCommand.getId(),
                        issueGiftCardCommand.getAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(GiftCardWasIssued giftCardWasIssued) {
        this.id = giftCardWasIssued.getId();
        this.remainingValue = giftCardWasIssued.getAmount();
    }

    @CommandHandler
    public void handle(RedeemGiftCardCommand redeemGiftCardCommand) {
        if (redeemGiftCardCommand.getAmount() <= 0) throw new IllegalArgumentException("amount is negative");
        if(redeemGiftCardCommand.getAmount() > this.remainingValue) throw new IllegalStateException("amount > remaining value");
        AggregateLifecycle.apply(
                new GiftCardWasIssued(
                        redeemGiftCardCommand.getId(),
                        redeemGiftCardCommand.getAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(GiftCardWasRedeemed giftCardWasRedeemed) {
        this.remainingValue -= giftCardWasRedeemed.getAmount();
    }
}
