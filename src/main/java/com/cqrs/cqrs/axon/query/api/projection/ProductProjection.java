package com.cqrs.cqrs.axon.query.api.projection;

import com.cqrs.cqrs.axon.command.api.data.Product;
import com.cqrs.cqrs.axon.command.api.data.ProductRepository;
import com.cqrs.cqrs.axon.command.api.model.ProductRestModel;
import com.cqrs.cqrs.axon.query.api.queries.GetProductsQuery;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductProjection {
    private ProductRepository productRepository;

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery) {
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(product -> ProductRestModel
                        .builder()
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .name(product.getName())
                        .build()
                ).collect(Collectors.toList());
    }
}
