package com.example.inventory.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import static com.lightbend.lagom.javadsl.api.Service.*;

/**
 * Microservice interface
 */
public interface ProductService extends Service {
    // Service is provided by Lagom, defines abstract method descriptor() for defining REST endpoints

    /**
     * Returns a ServiceCall of <<request-type>, <response-type>>
     */
    ServiceCall<NotUsed, String> test();

    ServiceCall<NotUsed, String> testUserService();

    @Override
    default Descriptor descriptor() {
        return named("product-service").withCalls(
                restCall(Method.GET, "/api/products/test", this::test), // exposed endpoint calls 'test()'
                restCall(Method.GET, "/api/products/test-user-service", this::testUserService)
        ).withAutoAcl(true); // Provide AccessControl for the endpoints
    }
}
