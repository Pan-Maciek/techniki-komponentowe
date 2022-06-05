package pl.edu.agh.ocr.logic

import net.sourceforge.tess4j.Tesseract
import net.sourceforge.tess4j.TesseractException
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pl.edu.agh.ocr.data.OcrResult
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

@Component
class OcrWorker(@Autowired private val logger: Logger, @Autowired tessData: String) {

    private val languages: List<String> = listOf("eng", "pol")

    private val tesseract = Tesseract()

    init {
        tesseract.setDatapath(Path(tessData).absolutePathString())
    }

    fun getExtractionResult(filePath: String): Pair<List<OcrResult>, List<String>> {
        logger.info("starting extracting text")
        var result = ""
        val file = File(filePath)

        if (!file.exists()) {
            return Pair(listOf(), listOf("file $filePath does not exist"))
        }

        val exceptions = mutableListOf<String>()
        val ocrResults = mutableListOf<OcrResult>()

        for (lang in languages) {
            try {
                result = getExtractionResult(lang, result, file)
            } catch (e: Exception) {
                logger.error("exception in extractText:\n${e.stackTraceToString()}")
                if (e.message != null) {
                    exceptions.add(e.message!!)
                }
            }
        }
        ocrResults.add(OcrResult(filePath, result))
        logger.info("filePath: $filePath, extracted text: $result")
        return Pair(ocrResults, exceptions)
    }

    private fun getExtractionResult(lang: String, result: String, file: File): String {
        tesseract.setLanguage(lang)
        var extractedText: String? = null
        try {
            extractedText = tesseract.doOCR(file)
        } catch (e: TesseractException) {
            logger.error(e.stackTraceToString())
            throw e
        }
        return if (extractedText != null && !result.contains(extractedText)) {
            logger.info("lang: $lang, extracted text: $extractedText")
            result.plus(" \n ").plus(extractedText)
        } else {
            logger.error("cannot extract text for lang: $lang")
            result
        }
    }
}