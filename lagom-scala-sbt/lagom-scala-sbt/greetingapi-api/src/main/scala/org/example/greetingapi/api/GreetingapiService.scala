package org.example.greetingapi.impl

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}

trait GreetingapiService extends Service {

  def requestGreeting(name: String): ServiceCall[NotUsed, String]

  def requestGreetingWithWeather(name: String): ServiceCall[WeatherRequest, String]

  override def descriptor: Descriptor = {
    import Service._

    named("greeting")
      .withCalls(
        restCall(Method.GET, "/api/greeting/:name", requestGreeting _),
        restCall(Method.POST, "/api/greeting/:name", requestGreetingWithWeather _)
      )
      .withAutoAcl(true)
  }
}

case class WeatherRequest(location: String)

object WeatherRequest {
  implicit val format: Format[WeatherRequest] = Json.format
}
