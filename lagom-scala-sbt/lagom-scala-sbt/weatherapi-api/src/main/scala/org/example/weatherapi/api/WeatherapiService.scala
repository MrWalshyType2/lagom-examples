package org.example.weatherapi.api

import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceAcl, ServiceCall}
import play.api.libs.json.{Format, Json}

/**
  * This service descriptor defines how to implement and invoke the weather service.
  */
trait WeatherapiService extends Service {

  def requestWeather(): ServiceCall[WeatherRequest, WeatherResponse]

  // Describes the endpoints of the api
  override def descriptor: Descriptor = {
    import Service._

    // creates a Descriptor with name 'weather'
    named("weather")
      .withCalls( // can also have restCall
        pathCall("/api/weather", requestWeather _) // endpoint "/api/weather" maps to requestWeather()
      )
      .withAutoAcl(true)
      .withAcls(ServiceAcl(pathRegex = Some("/api/play")))
  }
}

// Message definitions that the service will consume and produce
//  - must provide a implicit or customer message serializer for serializing and deserializing request & response messages
case class WeatherResponse(condition: String)

object WeatherResponse {
  // implicit JSON serialization using Play JSON
  implicit val format: Format[WeatherResponse] = Json.format
}

case class WeatherRequest(location: String)

object WeatherRequest {
  implicit val format: Format[WeatherRequest] = Json.format
}
