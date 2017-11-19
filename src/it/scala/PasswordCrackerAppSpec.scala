import java.nio.file.Paths

import app.PasswordCrackerApp
import helpers.DslBuilder._
import matchers.CustomMatchers
import org.scalatest.{AsyncFlatSpec, Matchers}

class PasswordCrackerAppSpec extends AsyncFlatSpec with Matchers with CustomMatchers
{
  "PasswordCrackerApp" should "return the correct password match results" in {

    val inputFile = Paths.get("src/it/resources/data.txt")

    PasswordCrackerApp.output(inputFile).map {
      _ should haveOutput ("we do what we must because we can" | "WRONG_PASSWORD" | "ab cd" | "zfzahm" | "gurwgrb" | "WRONG_PASSWORD")
    }
  }
}
