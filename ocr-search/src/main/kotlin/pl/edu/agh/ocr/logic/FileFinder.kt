package pl.edu.agh.ocr.logic

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.ocr.data.OcrResult
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream

@Component
class FileFinder(private val ocrWorker: OcrWorker) {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val extensions: List<String> = listOf(".png", ".jpg", "jpeg")

    fun extractFromDirectory(rootPath: String): Pair<List<OcrResult>, List<String>> {
        logger.info("Starting extracting text from images in path: '$rootPath'")
        val ocrResults = mutableListOf<OcrResult>()
        val exceptions = mutableListOf<String>()
        val stream = getOcrPathStream(rootPath).toList()
        val mapped = stream.map(ocrWorker::getExtractionResult)
        mapped.forEach { pair ->
            run {
                ocrResults.addAll(pair.first)
                exceptions.addAll(pair.second)
            }
        }
        logger.info("Finished extracting text from images in path: '$rootPath'")
        return Pair(ocrResults, exceptions)
    }

    private fun getOcrPathStream(rootPath: String): Stream<String> = Files.walk(Path.of(rootPath))
        .filter(this::isOcrFile)
        .map(Path::toString)

    private fun isOcrFile(filePath: Path): Boolean {
        val fileName = filePath.fileName.toString()
        return extensions.any { ex -> fileName.endsWith(ex) }
    }
}