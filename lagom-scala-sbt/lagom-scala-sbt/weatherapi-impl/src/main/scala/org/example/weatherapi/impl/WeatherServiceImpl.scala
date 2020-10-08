package org.example.weatherapi.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import org.example.weatherapi.api.{WeatherRequest, WeatherResponse, WeatherapiService}

import scala.concurrent.{ExecutionContext, Future}

class WeatherServiceImpl()(implicit ec: ExecutionContext) extends WeatherapiService {

  // does not execute the call, returns the call as a lambda,
  // returns a Future
  override def requestWeather(): ServiceCall[WeatherRequest, WeatherResponse] = ServiceCall {
    weatherRequest =>
      Future[WeatherResponse] {
        if (weatherRequest.location.equals("UK")) WeatherResponse("Raining")
        else WeatherResponse("Sunny")
      }
  }
}
