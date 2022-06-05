package logic

import org.slf4j.{Logger, LoggerFactory}

import java.nio.file.{Files, Path, Paths}
import java.util.Objects.nonNull
import result.Result.FileSearchResult

import scala.jdk.javaapi.CollectionConverters

class FileSearcher(path: String) {
  var errors: Seq[String] = Seq()

  val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def findInDir(phrases: Seq[String]): Seq[FileSearchResult] = {
    errors = Seq()

    logger.info(s"searching in dir for phrases: $phrases")

    val stream: Iterator[Path] = CollectionConverters.asScala(Files.walk(Paths.get(path)).iterator())

    stream
      .map(_.toString)
      .filter(_.toLowerCase.endsWith(".pdf"))
      .map(
        try {
          PdfSearcher.phraseOccurrences(phrases, _)
        } catch {
          case e: Throwable =>
            logger.error(e.toString)
            errors +:= e.toString
            null
        }
      )
      .filter(nonNull)
      .toSeq
  }
}
