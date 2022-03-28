import java.io.File

import org.apache.commons.lang3.StringUtils
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

object Main {
  def main(args: Array[String]): Unit = {
    println(pdfContainsPhrase("D:/tmp/KeepTalkingAndNobodyExplodes-BombDefusalManual-v2-pl.pdf", "bomb"))
  }

  private def pdfContainsPhrase(path: String, phrase: String): Boolean = {
    val text = retrieveTextFromFile(path)

    StringUtils.containsIgnoreCase(text, phrase)
  }

  private def retrieveTextFromFile(path: String): String = {
    val pdf = PDDocument.load(new File(path))
    val stripper = new PDFTextStripper

    stripper.getText(pdf)
  }
}
