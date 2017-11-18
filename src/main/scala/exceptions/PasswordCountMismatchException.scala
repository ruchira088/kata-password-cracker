package exceptions

case class PasswordCountMismatchException(expected: Int, actual: Int) extends Exception with MismatchValue[Int]
{
  override def getMessage = misMatchMessage
}
