package model.entity

import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.api.{ Identity, LoginInfo }
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import com.mohiva.play.silhouette.password.BCryptSha256PasswordHasher

import java.time.LocalDateTime
import java.util.UUID

case class User(
  id: UUID = UUID.randomUUID(),
  name: String,
  loginEmail: String,
  passwordHash: String,
  registeredAt: LocalDateTime,
  visitedAt: LocalDateTime,
  role: String,
  isEnabled: Boolean = false) extends Identity {

  def loginInfo: LoginInfo = LoginInfo(CredentialsProvider.ID, loginEmail)

  def passwordInfo: PasswordInfo = PasswordInfo(BCryptSha256PasswordHasher.ID, passwordHash)
}
