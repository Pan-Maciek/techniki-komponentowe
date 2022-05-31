package pl.edu.agh.ocr.utils

import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

object Utils {

    //    const val TESS_DATA = "/home/ocr_service/tessdata"
    val TESS_DATA: String = Path("tessdata").absolutePathString()         // todo

    val LANGUAGES: List<String> = listOf("eng", "pol")
}