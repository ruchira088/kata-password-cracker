package models

import utils.StringUtils

case class Scenario(passwords: List[String], loginAttempt: String)

object Scenario
{
  val EMPTY_STRING = ""
  val WRONG_PASSWORD = "WRONG_PASSWORD"

  def login(scenario: Scenario): String =
    accept(scenario.passwords, scenario.loginAttempt).fold(WRONG_PASSWORD)(_.mkString(" "))

  def accept(passwords: List[String], loginAttempt: String): Option[List[String]] =
    if (loginAttempt == EMPTY_STRING)
      Some(List.empty)
    else {
      val prefixMatches = passwords.filter(loginAttempt.startsWith)

      prefixMatches.foldLeft[Option[List[String]]](None) {

        case (output @ Some(_), _) => output

        case (None, password) => for {
            remainingLoginAttempt <- StringUtils.removePrefix(loginAttempt, password)
            passwordTrail <- accept(passwords, remainingLoginAttempt)
          }
          yield password :: passwordTrail
      }
    }
}