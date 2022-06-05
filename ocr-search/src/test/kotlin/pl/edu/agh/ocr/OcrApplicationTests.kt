package pl.edu.agh.ocr

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import pl.edu.agh.ocr.data.OcrResult
import pl.edu.agh.ocr.logic.FileFinder
import pl.edu.agh.ocr.logic.OcrService
import pl.edu.agh.ocr.logic.OcrWorker
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString
import kotlin.test.assertContains
import kotlin.test.assertEquals

internal class OcrApplicationTests {

    private val logger = LoggerFactory.getLogger(javaClass);

    private val ocrWorker = OcrWorker(logger, Path("./tessdata").absolutePathString())        //local tests
//    private val ocrWorker = OcrWorker()     // docker build tests

    @Test
    fun extractTextFromFile1Test() {
        val extracted = ocrWorker.getExtractionResult(Path("./test_images/test1.png").absolutePathString())        //local tests
//        val extracted = ocrWorker.getExtractionResult("/home/ocr/test_images/test1.png")     // docker build tests
        assertNotNull(extracted)
        val extractedText = extracted.first.joinToString { ocrResult -> ocrResult.textFound }
        assertContains(extractedText, "some test text")
        assertContains(extractedText, "more text")
    }

    @Test
    fun extractTextFromFile2Test() {
        val extracted = ocrWorker.getExtractionResult(Path("./test_images/test2.png").absolutePathString())        //local tests
//        val extracted = ocrWorker.getExtractionResult("/home/ocr/test_images/test2.png")     // docker build tests
        assertNotNull(extracted)
        val extractedText = extracted.first.joinToString { ocrResult -> ocrResult.textFound }
        assertContains(extractedText, "bLuUeee")
        assertContains(extractedText, "ReD TEXXt")
        assertContains(extractedText, "YellowT ext")
    }

    @Test
    fun shouldMapResult() {
        // given
        val ocrResults = listOf(OcrResult("path", "textsome more text number one"), OcrResult("path1", "text from image 1"))
        val phrases = listOf("some", "text")
        val service = OcrService(FileFinder(logger, ocrWorker))

        // when
        val results = service.getResults(ocrResults, phrases)

        // then
        assertNotNull(results)
        assertEquals(2, results.size)

        assertEquals(2, results[0].matches.size)
        assertContains(results[0].matches[0].indices, 4)
        assertContains(results[0].matches[1].indices, 0)
        assertContains(results[0].matches[1].indices, 14)

        assertEquals(1, results[1].matches.size)
        assertContains(results[1].matches[0].indices, 0)
    }
}
