import java.io.{File, InputStream}

import org.apache.commons.lang3.StringUtils
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

object Main {
  def main(args: Array[String]): Unit = {
    println(pdfContainsPhrase(getClass.getResourceAsStream("ktane-manual.pdf"), "bomb"))
  }

  private def pdfContainsPhrase(fileIS: InputStream, phrase: String): Boolean = {
    val text = retrieveTextFromFile(fileIS)

    StringUtils.containsIgnoreCase(text, phrase)
  }

  private def retrieveTextFromFile(fileIS: InputStream): String = {
    val pdf = PDDocument.load(fileIS)
    val stripper = new PDFTextStripper

    stripper.getText(pdf)
  }
}
