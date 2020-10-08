package org.example.weatherapi.impl

import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomServer}
import com.softwaremill.macwire.wire
import org.example.weatherapi.api.WeatherapiService
import play.api.libs.ws.ahc.AhcWSComponents

// Using compile-time dependency injection to wire the Lagom application together
// Lagom is using Macwire, provides macros to locate component dependencies
//  - abstract as it requires implementation of the method serviceLocator
abstract class WeatherApplication(context: LagomApplicationContext) extends LagomApplication(context)
  with AhcWSComponents {

  // Lagom uses lagomServer to discover service bindings
  //  - wire macro is used to inject dependencies into WeatherServiceImpl
  override lazy val lagomServer: LagomServer =
    serverFor[WeatherapiService](wire[WeatherServiceImpl])
      // injecting the Play router & appending to the Lagom server
      //  - to allow the endpoints to be published by the Service Gateway, add an ACL in the service descriptor
      .additionalRouter(wire[SimplePlayRouter].router)
}
