package logic

import java.nio.file.{Files, Path, Paths}
import java.util.Objects.nonNull

import result.Result.FileSearchResult

import scala.jdk.javaapi.CollectionConverters

class FileSearcher(path: String) {
  var errors: Seq[String] = Seq()

  def findInDir(phrases: Seq[String]): Seq[FileSearchResult] = {
    errors = Seq()

    val stream: Iterator[Path] = CollectionConverters.asScala(Files.walk(Paths.get(path)).iterator())

    stream
      .map(_.toString)
      .filter(_.toLowerCase.endsWith(".pdf"))
      .map(
        try {
          PdfSearcher.phraseOccurrences(phrases, _)
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
