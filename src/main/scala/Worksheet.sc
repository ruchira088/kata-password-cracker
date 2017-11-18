private def removePrefix(string: String, prefix: String): String =
  if (string.startsWith(prefix))
    string.substring(prefix.length)
  else
    string

removePrefix("elloWorld", "hello")