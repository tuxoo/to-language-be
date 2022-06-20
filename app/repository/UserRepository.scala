package repository

import com.mohiva.play.silhouette.api.LoginInfo
import model.entity.User

import java.util.UUID
import scala.concurrent.Future

trait UserRepository {
  def find(loginInfo: LoginInfo): Future[Option[User]]

  def save(user: User): Future[User]

  def update(user: User): Future[User]

  def updateIsEnable(id: UUID): Future[Int]

  def findById(id: UUID): Future[Option[User]]

  def findByEmail(email: String): Future[Option[User]]
}
