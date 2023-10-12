package com.cqrs.cqrs.axon.command.api.events;

import com.cqrs.cqrs.axon.command.api.data.Product;
import com.cqrs.cqrs.axon.command.api.data.ProductRepository;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
@AllArgsConstructor
public class ProductEventsHandler {
    private final ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        productRepository.save(product);
    }

    @ExceptionHandler
    public void handle(Exception e) throws Exception {
        throw e;
    }
}
