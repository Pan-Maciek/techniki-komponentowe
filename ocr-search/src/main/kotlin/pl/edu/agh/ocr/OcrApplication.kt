package pl.edu.agh.ocr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OcrApplication

fun main(args: Array<String>) {
    runApplication<OcrApplication>(*args)
}
