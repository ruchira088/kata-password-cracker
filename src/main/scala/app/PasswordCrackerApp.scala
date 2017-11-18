package app

import java.nio.file.Paths

import utils.FileUtils

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object PasswordCrackerApp
{
  def main(args: Array[String]): Unit =
  {
    val inputData = FileUtils.readFile(Paths.get("input/data.txt"))

    Await.ready(inputData.map(_.foreach(println)), 30 seconds)
  }
}
