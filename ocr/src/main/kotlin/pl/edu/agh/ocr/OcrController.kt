package pl.edu.agh.ocr

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OcrController {

    @CrossOrigin
    @GetMapping("/ocr")
    fun doOcr(): String {
        return ""
    }
}