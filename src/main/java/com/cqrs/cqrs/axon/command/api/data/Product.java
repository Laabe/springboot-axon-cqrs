package com.cqrs.cqrs.axon.command.api.data;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
public class Product {
    @Indexed()
    private String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
