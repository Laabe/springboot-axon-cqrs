package com.cqrs.cqrs.giftCard.queryCommand;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public class CardSummary {

    private String id;
    private Integer initialAmount;
    private Integer remainingAmount;

    public CardSummary deductAmount(Integer toBeDeducted) {
        return new CardSummary(this.id, this.initialAmount, this.remainingAmount - toBeDeducted);
    }
}