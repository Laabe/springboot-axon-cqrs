package com.cqrs.cqrs.axon.query.api.controller;


import com.cqrs.cqrs.axon.command.api.model.ProductRestModel;
import com.cqrs.cqrs.axon.query.api.queries.GetProductsQuery;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductQueryController {

    private final QueryGateway queryGateway;
    @GetMapping
    public List<ProductRestModel> getProducts() {
        GetProductsQuery getProductsQuery = new GetProductsQuery();
        return queryGateway.query(getProductsQuery,
                ResponseTypes.multipleInstancesOf(ProductRestModel.class))
                .join();
    }
}
