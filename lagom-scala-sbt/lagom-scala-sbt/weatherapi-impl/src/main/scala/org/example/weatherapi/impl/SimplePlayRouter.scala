package org.example.weatherapi.impl

import play.api.mvc.{DefaultActionBuilder, PlayBodyParsers, Results}
import play.api.routing.Router
import play.api.routing.sird._

class SimplePlayRouter(action: DefaultActionBuilder, parser: PlayBodyParsers) {

  // This router is wired in WeatherApplication
  val router = Router.from {
    case GET(p"/api/play") =>
      action(parser.default) { request =>
        Results.Ok("Play router resposne")
      }
  }
}
