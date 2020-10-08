package com.example.inventory.impl;

import akka.NotUsed;
import com.example.inventory.api.ProductService;
import com.example.inventory.user.api.UserService;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import javax.inject.Inject;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ProductServiceImpl implements ProductService {

    private final UserService userService;

    // Using dependency injection to get the userService microservice
    @Inject
    public ProductServiceImpl(UserService userService) {
        this.userService = userService;
    }

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
    public ServiceCall<NotUsed, String> testUserService() {
        return notUsed -> {
            return userService.test().invoke().thenApply(result -> {
                return result;
            });
        };
    }
}
