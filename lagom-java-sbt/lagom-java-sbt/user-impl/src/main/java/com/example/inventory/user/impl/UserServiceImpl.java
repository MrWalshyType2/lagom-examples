package com.example.inventory.user.impl;

import akka.NotUsed;
import com.example.inventory.user.api.UserService;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class UserServiceImpl implements UserService {

    @Override
    public ServiceCall<NotUsed, String> test() {
        return notUsed -> {
            return supplyAsync(() -> {
                return "User service is up";
            });
        };
    }
}
