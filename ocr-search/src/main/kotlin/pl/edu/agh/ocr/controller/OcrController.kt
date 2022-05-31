package pl.edu.agh.ocr.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.edu.agh.ocr.data.OcrResponse
import pl.edu.agh.ocr.logic.OcrService
import java.io.IOException

@RestController
class OcrController(private val ocrService: OcrService) {

    @CrossOrigin
    @GetMapping("/search")
    fun ocrSearch(
        @RequestParam phrases: List<String>,
        @RequestParam rootPath: String,
        @RequestParam lang: List<String>
    ): OcrResponse {
        return try {
            ocrService.getOcrResults(rootPath, phrases, lang)
        } catch (e: IOException) {
            OcrResponse(phrases, lang, "error", listOf(), listOf(e.stackTraceToString()))
        }
    }
}
