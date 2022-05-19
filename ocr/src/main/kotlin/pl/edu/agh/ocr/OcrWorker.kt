package pl.edu.agh.ocr

import net.sourceforge.tess4j.Tesseract
import net.sourceforge.tess4j.TesseractException
import org.slf4j.LoggerFactory
import pl.edu.agh.ocr.Utils.LANGUAGES
import pl.edu.agh.ocr.Utils.TESS_DATA
import java.io.File
import java.io.FileNotFoundException
import java.util.*
import kotlin.math.log

class OcrWorker(tessData: String = TESS_DATA) {

    private val tesseract = Tesseract()

    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        tesseract.setDatapath(tessData)
    }

    fun extractText(filePath: String): Pair<OcrResult?, List<Exception>> {
        logger.info("starting extracting text")
        var result = ""
        val file = File(filePath)

        if (!file.exists()) {
            return Pair(null, listOf(FileNotFoundException("file $filePath does not exist")))
        }

        val exceptions = mutableListOf<Exception>()

        for (lang in LANGUAGES) {
            try {
                result = extractText(lang, result, file)
            } catch (e: Exception) {
                logger.error("exception in extractText:\n${e.stackTraceToString()}")
                exceptions.add(e)
            }
        }
        if (result.isBlank()) {
            return Pair(null, exceptions)
        }
        logger.info("filePath: $filePath, extracted text: $result")
        return Pair(OcrResult(filePath, result), exceptions)
    }

    private fun extractText(lang: String, result: String, file: File): String {
        tesseract.setLanguage(lang)
        var extractedText: String? = null
        try {
            extractedText = tesseract.doOCR(file)
        } catch (e: TesseractException) {
            logger.error(e.stackTraceToString())
            throw e
        }
        return if (extractedText != null) {
            logger.info("lang: $lang, extracted text: $extractedText")
            result.plus(" \n ").plus(extractedText)
        } else {
            logger.error("cannot extract text for lang: $lang")
            result
        }
    }
}