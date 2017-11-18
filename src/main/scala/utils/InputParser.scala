package utils

import exceptions.{InputScenarioCountMismatchException, InvalidStringListLengthException, PasswordCountMismatchException}
import models.Scenario

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object InputParser
{
  val PASSWORD_SEPARATOR = " "

  def parseInt(string: String): Try[Int] =
    try {
      Success(string.toInt)
    } catch {
      case NonFatal(throwable) => Failure(throwable)
    }

  def parseScenario(inputString: List[String]): Try[Scenario] = inputString match
    {
      case passwordCount :: passwordsString :: loginAttempt :: Nil => {
        val passwordList = passwordsString.split(PASSWORD_SEPARATOR).toList

        for {
          count <- parseInt(passwordCount)

          _ <- ScalaUtils.predicate(
            count == passwordList.length,
            PasswordCountMismatchException(count, passwordList.length)
          )
        }
        yield Scenario(passwordList, loginAttempt)
      }

      case xs => Failure(InvalidStringListLengthException(xs.length))
    }

  def parseData(input: List[String]): List[Try[Scenario]] = input match
    {
      case Nil => List.empty

      case passwordCount :: passwordsString :: loginAttempt :: rest =>
        parseScenario(List(passwordCount, passwordsString, loginAttempt)) :: parseData(rest)
    }

  def parse(input: List[String]): Try[List[Try[Scenario]]] = input match
    {
      case countString :: rest => for
        {
          count <- parseInt(countString)

          data = parseData(rest)

          _ <- if (count == data.length) Success((): Unit)
                else Failure(InputScenarioCountMismatchException(count, data.length))
        }
        yield data
    }
}
