package com.cqrs.cqrs.axon.command.api.controller;

import com.cqrs.cqrs.axon.command.api.commands.CreateProductCommand;
import com.cqrs.cqrs.axon.command.api.model.ProductRestModel;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductCommandController {

    private CommandGateway commandGateway;

    @PostMapping
    public String addProduct(@RequestBody ProductRestModel productRestModel) {
        CreateProductCommand createProductCommand =
                CreateProductCommand
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .name(productRestModel.getName())
                        .price(productRestModel.getPrice())
                        .quantity(productRestModel.getQuantity())
                        .build();
        return commandGateway.sendAndWait(createProductCommand);
    }
}
