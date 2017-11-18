package utils

import scala.util.{Failure, Success, Try}

object ScalaUtils
{
  def predicate(booleanCondition: Boolean, exception: => Exception): Try[Unit] =
    if (booleanCondition)
      Success((): Unit)
    else
      Failure(exception)
}
