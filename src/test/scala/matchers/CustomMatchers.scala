package matchers

import helpers.DslBuilder._
import org.scalatest.matchers.{MatchResult, Matcher}

trait CustomMatchers {

  def haveOutput(stringList: StringList): Matcher[List[String]] =
    Matcher {
      left => MatchResult(
        left == stringList.strings,
        s"$left does NOT equal ${stringList.strings}",
        s"$left does equal ${stringList.strings}"
      )
    }

}
