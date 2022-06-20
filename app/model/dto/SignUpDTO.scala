package model.dto

import play.api.libs.json.{ Json, OFormat }

case class SignUpDTO(
  name: String,
  email: String,
  password: String)

object SignUpDTO {

  implicit val format: OFormat[SignUpDTO] = Json.format[SignUpDTO]
}
