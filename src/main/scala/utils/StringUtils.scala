package utils

object StringUtils
{
  def removePrefix(string: String, prefix: String): Option[String] =
    if (string.startsWith(prefix))
      Some(string.substring(prefix.length))
    else
      None
}
