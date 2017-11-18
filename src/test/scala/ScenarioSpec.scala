import models.Scenario
import org.scalatest.{Assertion, FlatSpec, Matchers}

class ScenarioSpec extends FlatSpec with Matchers
{
  case class StringList(strings: List[String]) {
    def | (string: String) = StringList(strings :+ string)
  }

  object StringList {
    implicit def toOptionStringList(stringList: StringList): Option[List[String]] = Some(stringList.strings)
  }

  implicit class StringPair(string: String) {
    def | (anotherString: String): StringList = StringList(List(string, anotherString))
  }

  implicit class ScenarioBuilder(stringList: StringList) {
    def tryingWith(loginAttempt: String) = Scenario(stringList.strings, loginAttempt)
  }

  implicit class ScenarioTestCase(scenario: Scenario) {
    def shouldResult(result: Option[List[String]]): Assertion =
      Scenario.accept(scenario.passwords, scenario.loginAttempt) shouldEqual result
  }

  "Scenario" should "accept login attempt" in {

    "because" | "can" | "do" | "must" | "we" | "what" tryingWith "wedowhatwemustbecausewecan" shouldResult
      "we" | "do" | "what" | "we" | "must" | "because" | "we" | "can"
  }

  it should "REJECT login attempt" in {

    "hello" | "world" tryingWith "helloplanet" shouldResult None
  }
}
