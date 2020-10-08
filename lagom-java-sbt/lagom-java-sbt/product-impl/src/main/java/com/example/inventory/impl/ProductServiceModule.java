package com.example.inventory.impl;

import com.example.inventory.api.ProductService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

// This Module has to be indicated as providing config for the ProductService in application.conf
public class ProductServiceModule extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        // bind the api to a service
        bindService(ProductService.class, ProductServiceImpl.class);
    }
}
