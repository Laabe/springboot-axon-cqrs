package com.cqrs.cqrs.giftCard.queryCommand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class FetchCardSummariesQuery {
    private Integer size;
    private Integer offset;
}
