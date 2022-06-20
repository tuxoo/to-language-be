package service

import com.mohiva.play.silhouette.api.LoginInfo
import model.dto.SignUpDTO
import model.entity.User
import model.enums.Roles
import repository.UserRepository

import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ ExecutionContext, Future }

class UserServiceImpl @Inject() (userRepository: UserRepository)(implicit ex: ExecutionContext) extends UserService {

  override def retrieve(loginInfo: LoginInfo): Future[Option[User]] = userRepository.find(loginInfo)

  def save(signUpDTO: SignUpDTO): Future[User] = {
    val user = User(
      name = signUpDTO.name,
      loginEmail = signUpDTO.email,
      passwordHash = signUpDTO.password,
      registeredAt = LocalDateTime.now(),
      visitedAt = LocalDateTime.now(),
      role = Roles.USER.toString)

    userRepository.save(user)
  }

  def verifyUser(id: UUID): Future[Int] = userRepository.updateIsEnable(id)

  def getById(id: UUID): Future[Option[User]] = userRepository.findById(id)

  def getByLoginEmail(email: String): Future[Option[User]] = userRepository.findByEmail(email)
}
