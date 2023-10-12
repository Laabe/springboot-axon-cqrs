package com.cqrs.cqrs.giftCard.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class GiftCardWasIssued {
    private String id;
    private Integer amount;
}
