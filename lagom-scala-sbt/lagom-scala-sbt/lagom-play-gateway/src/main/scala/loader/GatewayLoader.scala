package scala.loader

import com.lightbend.lagom.scaladsl.akka.discovery.AkkaDiscoveryComponents
import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.{Application, ApplicationLoader, Mode}

class GatewayLoader extends ApplicationLoader {

  override def load(context: ApplicationLoader.Context): Application = {
    context.environment.mode match {
      case Mode.Dev => loadDevMode(context)
      case _ => loadInternal(context)
    }
  }

  def loadInternal(context: ApplicationLoader.Context): Application =
    (new Gateway(context, Seq.empty) with AkkaDiscoveryComponents).application

  def loadDevMode(context: ApplicationLoader.Context): Application =
    (new Gateway(context, Seq.empty) with LagomDevModeComponents).application
}
