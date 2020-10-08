package org.example.greetingapi.impl
import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.ServiceCall
import javax.inject.Inject
import org.example.weatherapi.api.{WeatherResponse, WeatherapiService, WeatherRequest => WeatherApiRequest}

import scala.concurrent.{ExecutionContext, Future}

class GreetingServiceImpl(weatherapiService: WeatherapiService)(implicit ec: ExecutionContext) extends GreetingapiService {

  override def requestGreeting(name: String): ServiceCall[NotUsed, String] = ServiceCall {
    notUsed =>
      Future[String] {
        s"Hello $name"
      }
  }

  override def requestGreetingWithWeather(name: String): ServiceCall[WeatherRequest, String] = ServiceCall {
    weatherRequest =>
      val result: Future[WeatherResponse] =
        weatherapiService.requestWeather.invoke(WeatherApiRequest(weatherRequest.location))

      result.map { weatherResponse =>
        s"Hello $name, the weather in ${weatherRequest.location} is ${weatherResponse.condition} today."
      }
  }
}
