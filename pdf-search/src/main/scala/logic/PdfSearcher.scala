package logic

import java.io.File
import java.util.Objects.nonNull
import org.apache.commons.lang3.StringUtils
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.slf4j.{Logger, LoggerFactory}
import result.Result.{FileSearchResult, Matches}

object PdfSearcher {
  val stripper = new PDFTextStripper

  val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def phraseOccurrences(phrases: Seq[String], path: String): FileSearchResult = {
    logger.info(s"searching in file: $path")
    val pdf = PDDocument.load(new File(path))

    val pdfText = stripper.getText(pdf).toLowerCase

    val paragraphRes: Seq[Matches] = pdfText
      .split(stripper.getLineSeparator)
      .map(line => {
        val indices: Seq[Int] = phrases
          .map { _.toLowerCase }
          .flatMap { indicesOf(line, _) }

        if (indices.nonEmpty) Matches(line, indices)
        else null
      })
      .filter(nonNull)
      .toSeq

    pdf.close()

    logger.info(s"found: $paragraphRes")

    if (paragraphRes.nonEmpty) FileSearchResult(path, paragraphRes)
    else null
  }

  def indicesOf(input: String, phrase: String): Seq[Int] = {
    var indices = Seq[Int]()
    var index = 0
    while (index >= 0) {
      index = StringUtils.indexOf(input, phrase, index + 1)
      if (index >= 0) {
        indices :+= index
      }
    }
    indices
  }
}
