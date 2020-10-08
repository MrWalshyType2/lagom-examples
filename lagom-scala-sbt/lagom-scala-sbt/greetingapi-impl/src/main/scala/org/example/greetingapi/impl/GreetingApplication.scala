package org.example.greetingapi.impl

import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomServer}
import com.softwaremill.macwire.wire
import org.example.weatherapi.api.WeatherapiService
import play.api.libs.ws.ahc.AhcWSComponents

abstract class GreetingApplication(context: LagomApplicationContext) extends LagomApplication(context)
  with AhcWSComponents {

  lazy val weatherService = serviceClient.implement[WeatherapiService]

  override def lagomServer: LagomServer =
    serverFor[GreetingapiService](wire[GreetingServiceImpl])
}
