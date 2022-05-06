package logic

import java.io.File
import java.util.Objects.nonNull
import org.apache.commons.lang3.StringUtils
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import result.Result.{FileSearchResult, Matches}

object PdfSearcher {
  val stripper = new PDFTextStripper

  def phraseOccurrences(phrases: Seq[String], path: String): FileSearchResult = {
    val pdf = PDDocument.load(new File(path))

    val pdfText = stripper.getText(pdf).toLowerCase


    val paragraphRes: Seq[Matches] = pdfText
      .split(stripper.getLineSeparator)
      .map(line => {
        var indices: Seq[Int] = Seq()

        phrases
          .map(phrase => phrase.toLowerCase)
          .foreach(phrase => {
            var index = 0
            while (index >= 0) {
              index = StringUtils.indexOf(line, phrase, index + 1)
              if (index >= 0) {
                indices :+= index
              }
            }
          }
          )

        if (indices.nonEmpty)
          Matches(line, indices)
        else
          null
      })
      .filter(nonNull)
      .toSeq

    pdf.close()

    if (!paragraphRes.isEmpty) FileSearchResult(path, paragraphRes) else null
  }
}
