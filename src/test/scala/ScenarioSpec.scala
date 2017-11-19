import org.scalatest.FlatSpec
import helpers.DslBuilder._

class ScenarioSpec extends FlatSpec
{
  "Scenario" should "accept login attempt" in {

    "because" | "can" | "do" | "must" | "we" | "what" tryingWith "wedowhatwemustbecausewecan" shouldResult
      "we" | "do" | "what" | "we" | "must" | "because" | "we" | "can"
  }

  it should "REJECT login attempt" in {

    "hello" | "world" tryingWith "helloplanet" shouldResult None
  }
}
