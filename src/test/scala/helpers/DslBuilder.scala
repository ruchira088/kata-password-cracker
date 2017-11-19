package helpers

import models.Scenario
import org.scalatest.{Assertion, Matchers}

object DslBuilder extends Matchers
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
}
