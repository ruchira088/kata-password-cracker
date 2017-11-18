package exceptions

trait MismatchValue[A] {
  mismatchValue: Exception =>

  def expected: A

  def actual: A

  def misMatchMessage = s"expected was $expected but the actual is $actual."

}
