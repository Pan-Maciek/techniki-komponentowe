package logic

import java.nio.file.{Files, Path}
import java.util.Objects.nonNull

import result.Result.FileSearchResult

import scala.jdk.javaapi.CollectionConverters

class FileSearcher(path: String) {
  var errors: Seq[String] = Seq()

  def findInDir(phrase: String): Seq[FileSearchResult] = {
    errors = Seq()

    val stream: Iterator[Path] = CollectionConverters.asScala(Files.walk(Path.of(path)).iterator())

    stream
      .map(_.toString)
      .filter(_.toLowerCase.endsWith(".pdf"))
      .map(
        try {
          PdfSearcher.phraseOccurrences(phrase, _)
        } catch {
          case e: Throwable =>
            errors +:= e.toString
            null
        }
      )
      .filter(nonNull)
      .toSeq
  }
}
