package com.example.inventory.impl;

import akka.NotUsed;
import com.example.inventory.api.ProductService;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ProductServiceImpl implements ProductService {

    @Override
    public ServiceCall<NotUsed, String> test() {
        // takes an empty request body, returns a String
        return notUsed -> {
            return supplyAsync(() -> {
                return "Product service is up";
            });
        };
    }

    @Override
    public Descriptor descriptor() {
        return null;
    }
}
