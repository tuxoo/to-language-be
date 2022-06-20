package model.dto

import play.api.libs.json.{ Json, OFormat }

case class SignInDTO(
  email: String,
  password: String)

object SignInDTO {

  implicit val format: OFormat[SignInDTO] = Json.format[SignInDTO]
}
