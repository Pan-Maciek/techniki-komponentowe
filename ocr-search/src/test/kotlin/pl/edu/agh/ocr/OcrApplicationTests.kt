package pl.edu.agh.ocr

import org.junit.jupiter.api.Test
import pl.edu.agh.ocr.logic.OcrWorker
import pl.edu.agh.ocr.utils.Utils
import java.io.File

//@SpringBootTest
class OcrApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Test
    fun test() {
        val file = File(Utils.TESS_DATA)
        println(file.isDirectory)
        println(file.absolutePath)
        val ocrWorker = OcrWorker()
        val extractText = ocrWorker.getExtractionResult("C:\\Users\\Wojtek\\Downloads\\test\\test_images\\test1.png")
        println(extractText)
    }

}
