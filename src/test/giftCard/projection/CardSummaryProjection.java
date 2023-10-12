package com.cqrs.cqrs.giftCard.projection;

import com.cqrs.cqrs.giftCard.event.GiftCardWasIssued;
import com.cqrs.cqrs.giftCard.event.GiftCardWasRedeemed;
import com.cqrs.cqrs.giftCard.queryCommand.CardSummary;
import com.cqrs.cqrs.giftCard.queryCommand.FetchCardSummariesQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class CardSummaryProjection {

    private final List<CardSummary> cardSummaries = new CopyOnWriteArrayList<>();

    @EventHandler
    public void on(GiftCardWasIssued giftCardWasIssued) {
        CardSummary cardSummary = new CardSummary(
                giftCardWasIssued.getId(),
                giftCardWasIssued.getAmount(),
                giftCardWasIssued.getAmount()
        );
        cardSummaries.add(cardSummary);
    }

    @EventHandler
    public void on(GiftCardWasRedeemed giftCardWasRedeemed) {
        cardSummaries.stream()
                .filter(cs -> giftCardWasRedeemed.getId().equals(cs.getId()))
                .findFirst()
                .ifPresent(cardSummary -> {
                    CardSummary updatedCardSummary = cardSummary.deductAmount(giftCardWasRedeemed.getAmount());
                    cardSummaries.remove(cardSummary);
                    cardSummaries.add(updatedCardSummary);
                });
    }

    @QueryHandler
    public List<CardSummary> fetch(FetchCardSummariesQuery query) {
        return cardSummaries.stream()
                .skip(query.getOffset())
                .limit(query.getSize())
                .collect(Collectors.toList());
    }
}