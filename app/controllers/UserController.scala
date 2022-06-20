package controllers

import com.mohiva.play.silhouette.api.actions.SecuredRequest
import com.mohiva.play.silhouette.api.exceptions.ProviderException
import com.mohiva.play.silhouette.api.util.Credentials
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import model.dto._
import model.entity.User
import play.api.i18n.Lang
import play.api.libs.json.{JsString, Json, OFormat}
import play.api.mvc._
import utils.{JWTEnvironment, WithProvider}

import java.util.UUID
import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class UserController @Inject()(components: SilhouetteControllerComponents)(implicit ex: ExecutionContext) extends SilhouetteController(components) {

  implicit val userFormat: OFormat[IdentityType] = Json.format[User]

  def signUp: Action[AnyContent] = UnsecuredAction.async { implicit request: Request[AnyContent] =>
    implicit val lang: Lang = supportedLangs.availables.head
    request.body.asJson.flatMap(_.asOpt[SignUpDTO]) match {
      case Some(newUser) if newUser.password.nonEmpty =>
        userService.getByLoginEmail(newUser.email).flatMap {
          case Some(_) => Future.successful(Conflict(JsString(messagesApi("user.already.exist"))))
          case None =>
            val passwordInfo = passwordHasherRegistry.current.hash(newUser.password)
            val user = newUser.copy(password = passwordInfo.password)
            userService.save(user).map(_ => Created)
        }
      case _ => Future.successful(BadRequest(JsString(messagesApi("invalid.body"))))
    }
  }

  def verify(code: String): Action[AnyContent] = UnsecuredAction.async { implicit request: Request[AnyContent] =>
    implicit val lang: Lang = supportedLangs.availables.head
    if (code.isEmpty) {
      Future.successful(BadRequest(JsString(messagesApi("code is empy"))))
    } else {
      try {
        val id = UUID.fromString(code)
        userService.getById(id).flatMap {
          case Some(user) =>
            userService.verifyUser(user.id)
            Future.successful(Ok)
          case None => Future.successful(BadRequest(JsString(messagesApi("illegal code"))))
        }
      } catch {
        case _: IllegalArgumentException => Future.successful(BadRequest(JsString(messagesApi("code is not UUID"))))
      }
    }
  }

  def signIn: Action[AnyContent] = UnsecuredAction.async { implicit request: Request[AnyContent] =>
    implicit val lang: Lang = supportedLangs.availables.head
    request.body.asJson.flatMap(_.asOpt[SignInDTO]) match {
      case Some(signInModel) =>
        val credentials = Credentials(signInModel.email, signInModel.password)
        credentialsProvider.authenticate(credentials).flatMap { loginInfo =>
          userService.retrieve(loginInfo).flatMap {
            case Some(_) =>
              for {
                authenticator <- authenticatorService.create(loginInfo)
                token <- authenticatorService.init(authenticator)
              } yield {
                Ok(Json.toJson(TokenDTO(token)))
              }
            case None => Future.successful(BadRequest(JsString(messagesApi("could.not.find.user"))))
          }
        }.recover {
          case _: ProviderException => BadRequest(JsString(messagesApi("invalid.credentials")))
        }
      case None => Future.successful(BadRequest(JsString(messagesApi("could.not.find.user"))))
    }
  }

  def getUserProfile: Action[AnyContent] = SecuredAction(WithProvider[AuthType](CredentialsProvider.ID)).async {
    _: SecuredRequest[JWTEnvironment, AnyContent] =>
      implicit val lang: Lang = supportedLangs.availables.head
      Future.successful(BadRequest(JsString(messagesApi("invalid.body"))))
  }
}
