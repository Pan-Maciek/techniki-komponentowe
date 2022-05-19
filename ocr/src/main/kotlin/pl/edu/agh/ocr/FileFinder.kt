package pl.edu.agh.ocr

import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream

class FileFinder {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val extensions: List<String> = listOf(".png", ".jpg", "jpeg")

//    private val exceptions: List<String> = mutableListOf()

    fun findInDirectory(rootPath: String): List<OcrResult> {
        val ocrWorker = OcrWorker()
//        return getOcrPathStream(rootPath).map(filePath -> {
//            val resultPair: Pair<OcrResult?, List<Exception>> = ocrWorker.extractText(filePath)
//            val ocrResult: OcrResult? = resultPair.first
//            return ocrResult
//            ) }
        val q = getOcrPathStream(rootPath).map { filePath -> ocrWorker.extractText(filePath) }.toli
        val exceptions = q.map { pair -> pair.second}.toList()
    }

    private fun getOcrPathStream(rootPath: String): Stream<String> {
        return Files.walk(Path.of(rootPath))
            .filter(this::isOcrFile)
            .map(Path::toString)
    }

    private fun isOcrFile(fileName: Path): Boolean {
        val fileNameString = fileName.fileName.toString()
        return extensions.any { ex -> fileNameString.endsWith(ex) }
    }
}