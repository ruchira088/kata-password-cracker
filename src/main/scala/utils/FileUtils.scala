package utils

import java.nio.ByteBuffer
import java.nio.channels.{AsynchronousFileChannel, CompletionHandler}
import java.nio.file.{Path, StandardOpenOption}

import scala.concurrent.{Future, Promise}
import scala.util.control.NonFatal

object FileUtils
{
  val DEFAULT_BYTE_BUFFER_SIZE = 1024

  def readFile(filePath: Path): Future[List[String]] =
  {
    val promise = Promise[List[String]]

    try {
      val fileChannel = AsynchronousFileChannel.open(filePath, StandardOpenOption.READ)
      val byteBuffer = ByteBuffer.allocate(DEFAULT_BYTE_BUFFER_SIZE)

      fileChannel.read(byteBuffer, 0, (): Unit, new CompletionHandler[Integer, Unit]
      {
        override def failed(throwable: Throwable, unit: Unit) = {
          promise.failure(throwable)
        }

        override def completed(integer: Integer, unit: Unit) = {
          promise.success(new String(byteBuffer.array).trim.split("\n").toList)
        }
      })
    } catch {
      case NonFatal(throwable) => promise.failure(throwable)
    }

    promise.future
  }
}
