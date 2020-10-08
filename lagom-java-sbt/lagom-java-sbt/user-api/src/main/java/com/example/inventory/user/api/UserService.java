package com.example.inventory.user.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import static com.lightbend.lagom.javadsl.api.Service.*;

public interface UserService extends Service {

    ServiceCall<NotUsed, String> test();

    @Override
    default Descriptor descriptor() {
        return named("user-service").withCalls(
                restCall(Method.GET, "/api/users/test", this::test)
        ).withAutoAcl(true);
    }
}
