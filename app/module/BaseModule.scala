package module

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import repository.{ UserRepository, UserRepositoryImpl }
import service.{ UserService, UserServiceImpl }

class BaseModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[UserRepository].to[UserRepositoryImpl]
    bind[UserService].to[UserServiceImpl]
  }

}
