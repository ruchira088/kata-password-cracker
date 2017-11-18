package models

import utils.StringUtils

case class Scenario(passwords: List[String], loginAttempt: String)

object Scenario
{
  val EMPTY_STRING = ""

  def accept(passwords: List[String], loginAttempt: String): Option[List[String]] =
    if (loginAttempt == EMPTY_STRING)
      Some(List.empty)
    else {
      val prefixMatches = passwords.filter(loginAttempt.startsWith)

      prefixMatches.foldLeft[Option[List[String]]](None) {
        case (output @ Some(_), _) => output
        case (None, password) => for {
            rest <- accept(passwords, StringUtils.removePrefix(loginAttempt, password))
          }
          yield password :: rest
      }
    }
}