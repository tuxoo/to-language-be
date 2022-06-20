package service

import com.mohiva.play.silhouette.api.services.IdentityService
import model.dto.SignUpDTO
import model.entity.User

import java.util.UUID
import scala.concurrent.Future

trait UserService extends IdentityService[User] {

  def save(signUpDTO: SignUpDTO): Future[User]

  def verifyUser(id: UUID): Future[Int]

  def getById(id: UUID): Future[Option[User]]

  def getByLoginEmail(email: String): Future[Option[User]]
}
