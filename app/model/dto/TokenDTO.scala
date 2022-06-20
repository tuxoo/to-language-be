package model.dto

import play.api.libs.json.{ Json, OFormat }

case class TokenDTO(
  token: String)

object TokenDTO {

  implicit val format: OFormat[TokenDTO] = Json.format[TokenDTO]
}
