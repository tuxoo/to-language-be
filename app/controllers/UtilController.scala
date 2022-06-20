package controllers

import play.api.libs.json.JsString
import play.api.mvc._

import javax.inject._
import scala.concurrent.ExecutionContext

class UtilController @Inject() (components: SilhouetteControllerComponents)(implicit ex: ExecutionContext)
  extends SilhouetteController(components) {

  def ping(): Action[AnyContent] = UnsecuredAction { implicit request: Request[AnyContent] =>
    Ok(JsString("pong"))
  }
}
