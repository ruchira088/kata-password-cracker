package exceptions

case class InputScenarioCountMismatchException(expected: Int, actual: Int) extends Exception with MismatchValue[Int]
{
  override def getMessage = misMatchMessage
}
