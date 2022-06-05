package pl.edu.agh.ocr.controller

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.edu.agh.ocr.data.OcrResponse
import pl.edu.agh.ocr.logic.OcrService
import java.io.IOException

@RestController
class OcrController(@Autowired private val ocrService: OcrService, @Autowired private val logger: Logger) {

    @CrossOrigin
    @GetMapping("/search")
    fun ocrSearch(
        @RequestParam phrases: List<String>,
        @RequestParam rootPath: String,
        @RequestParam lang: List<String>
    ): OcrResponse {
        logger.info("Received: phrases=$phrases, rootPath=$rootPath, lang=$lang")
        return try {
            ocrService.getOcrResults(rootPath, phrases, lang)
        } catch (e: IOException) {
            OcrResponse(phrases, lang, "error", listOf(), listOf(e.stackTraceToString()))
        }
    }
}
