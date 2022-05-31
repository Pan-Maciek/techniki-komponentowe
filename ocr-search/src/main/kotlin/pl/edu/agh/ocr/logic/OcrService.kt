package pl.edu.agh.ocr.logic

import org.springframework.stereotype.Service
import pl.edu.agh.ocr.data.OcrResponse
import pl.edu.agh.ocr.data.OcrResult
import pl.edu.agh.ocr.logic.FileFinder

@Service
class OcrService(private val fileFinder: FileFinder) {

    fun getOcrResults(rootPath: String, phrases: List<String>, lang: List<String>): OcrResponse {
        val ocrResults: Pair<List<OcrResult>, List<String>> = fileFinder.extractFromDirectory(rootPath)
        return OcrResponse(phrases, lang, "ok", ocrResults.first, ocrResults.second)
    }
}
