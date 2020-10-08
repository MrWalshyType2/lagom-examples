package com.example.inventory.impl;

import com.example.inventory.api.ProductService;
import org.junit.Test;
import com.lightbend.lagom.javadsl.testkit.ServiceTest.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class ProductServiceTest {

    @Test
    public void testProductService() {
//        withServer(defaultSetup(), server -> {
//            final ProductService service = server.client(ProductService.class);
//            final String serviceResponse = service.test().invoke().toCompletableFuture().get(5, TimeUnit.SECONDS);
//            assertTrue("Product service is up", serviceResponse.contains("up"));
//        });
    }
}
