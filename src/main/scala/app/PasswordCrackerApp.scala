package app

import java.nio.file.{Path, Paths}

import models.Scenario
import utils.{FileUtils, InputParser}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future.fromTry
import scala.util.{Failure, Success}

object PasswordCrackerApp
{
  val INPUT_FILE: Path = Paths.get("input/data.txt")

  def main(args: Array[String]): Unit =
  {
    val outcomes = for {
      inputData <- FileUtils.readFile(INPUT_FILE)

      scenarios <- fromTry(InputParser.parse(inputData))

      results = scenarios.map {
        case Success(scenario) => Scenario.login(scenario)
        case Failure(throwable) => throwable.getMessage
      }

    } yield results

    val outputResult = Await.result(outcomes, 30 seconds)

    outputResult.foreach(println)
  }
}
