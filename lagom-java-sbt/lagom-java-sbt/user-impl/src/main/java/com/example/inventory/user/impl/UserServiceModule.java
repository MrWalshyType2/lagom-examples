package com.example.inventory.user.impl;

import com.example.inventory.user.api.UserService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

public class UserServiceModule extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        bindService(UserService.class, UserServiceImpl.class);
    }
}
