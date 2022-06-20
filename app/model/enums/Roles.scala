package model.enums

object Roles extends Enumeration {
  type Role = Value

  val ADMIN: Roles.Value = Value("ADMIN")
  val USER: Roles.Value = Value("USER")
}
