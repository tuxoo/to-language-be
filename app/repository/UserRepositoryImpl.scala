package repository

import com.mohiva.play.silhouette.api.LoginInfo
import model.entity.User
import play.api.db.slick.DatabaseConfigProvider
import repository.table.UserTable
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ ExecutionContext, Future }

class UserRepositoryImpl @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends UserRepository {

  private val users = TableQuery[UserTable]
  private val db = dbConfigProvider.get[JdbcProfile].db

  override def find(loginInfo: LoginInfo): Future[Option[User]] = db.run {
    users.filter(_.loginEmail === loginInfo.providerKey).result.headOption
  }

  def save(user: User): Future[User] = db.run {
    users returning users += user
  }

  override def update(user: User): Future[User] = db.run {
    users.filter(_.loginEmail === user.loginEmail).update(user).map(_ => user)
  }

  def updateIsEnable(id: UUID): Future[Int] = db.run {
    users.filter(_.id === id).map(_.isEnabled).update(true)
  }

  def findById(id: UUID): Future[Option[User]] = db.run {
    users.filter(_.id === id).result.headOption
  }

  def findByEmail(email: String): Future[Option[User]] = db.run {
    users.filter(_.loginEmail === email).result.headOption
  }
}
