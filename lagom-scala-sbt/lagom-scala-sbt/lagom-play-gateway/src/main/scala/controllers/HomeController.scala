package scala.controllers

import javax.inject.{Inject, Singleton}
import org.example.greetingapi.impl.GreetingapiService
import play.api.mvc._
import play.api._
import play.api.libs.json.Json

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HomeController @Inject()(val greetingapiService: GreetingapiService,
                               val controllerComponents: ControllerComponents)(
                               implicit ec: ExecutionContext) extends BaseController {

  def index() = Action.async {
    implicit request =>
      val hello = greetingapiService.requestGreeting("Fred").invoke()
      hello.map(s => Ok(Json.toJson(s)))

//      Future(Ok(views.html.index()))
  }
}
