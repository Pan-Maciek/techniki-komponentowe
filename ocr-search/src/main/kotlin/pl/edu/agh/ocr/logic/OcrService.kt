package pl.edu.agh.ocr.logic

import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.edu.agh.ocr.data.Match
import pl.edu.agh.ocr.data.OcrResponse
import pl.edu.agh.ocr.data.OcrResult
import pl.edu.agh.ocr.data.SearchResult

@Service
class OcrService(@Autowired private val fileFinder: FileFinder) {

    fun getOcrResults(rootPath: String, phrases: List<String>, lang: List<String>): OcrResponse {
        val ocrResults: Pair<List<OcrResult>, List<String>> = fileFinder.extractFromDirectory(rootPath)
        return OcrResponse(phrases, lang, "ok", getResults(ocrResults.first, phrases), ocrResults.second)
    }

    fun getResults(results: List<OcrResult>, phrases: List<String>): List<SearchResult> {
        return results.mapNotNull { ocrResult -> getResult(ocrResult, phrases) }
    }

    fun getResult(ocrResult: OcrResult, phrases: List<String>): SearchResult? {
        val matches = phrases.mapNotNull { phrase -> getMatch(ocrResult, phrase.lowercase()) }
        return if (matches.isNotEmpty()) SearchResult(ocrResult.filePath, matches) else null
    }

    fun getMatch(result: OcrResult, phrase: String): Match? {
        val text = result.textFound.lowercase()
        val indices = mutableListOf<Int>()
        var index = 0
        do {
            index = StringUtils.indexOf(text, phrase, index)
            if (index >= 0) {
                indices.add(index)
                index++
            }
        } while (index < text.length && index >= 0)
        return if (indices.isNotEmpty()) Match(result.textFound, indices) else null
    }
}
