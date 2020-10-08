package org.example.weatherapi.impl

import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceLocator}
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomApplicationLoader}
import org.example.weatherapi.api.WeatherapiService

// ApplicationLoader allows the app to bootstrap itself
class WeatherLoader extends LagomApplicationLoader {

  // required
  override def load(context: LagomApplicationContext): LagomApplication = {
    new WeatherApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }
  }

  // required
  override def loadDevMode(context: LagomApplicationContext): LagomApplication = {
    new WeatherApplication(context) with LagomDevModeComponents
  }

  // Optional, useful with component configuration (service gateways f.e.)
  override def describeService: Option[Descriptor] = Some(readDescriptor[WeatherapiService])
}
