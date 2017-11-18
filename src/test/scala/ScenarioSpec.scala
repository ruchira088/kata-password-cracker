import models.Scenario
import org.scalatest.FlatSpec

class ScenarioSpec extends FlatSpec
{
  case class PasswordList(passwords: List[String]) {
    def | (password: String) = PasswordList(passwords :+ password)
  }

  implicit class PasswordPair(password: String) {
    def | (anotherPassword: String): PasswordList = PasswordList(List(password, anotherPassword))
  }

  "Scenario" should "accept login attempt" in {

    val passwords = "because" | "can" | "do" | "must" | "we" | "what"
    val loginAttempt = "wedowhatwemustbecausewecan"

    println(Scenario.accept(passwords.passwords, loginAttempt))
  }
}
