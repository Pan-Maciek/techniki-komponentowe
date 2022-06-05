package pl.edu.agh.ocr

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

@SpringBootApplication
class OcrApplication {
    @Bean
    @Scope("prototype")
    fun logger(injectionPoint: InjectionPoint): Logger =
        LoggerFactory.getLogger(injectionPoint.methodParameter?.containingClass ?: injectionPoint.field?.declaringClass)

    @Bean
    @Scope("prototype")
    fun tessData(injectionPoint: InjectionPoint): String = "/home/ocr/tessdata"
//    fun tessData(injectionPoint: InjectionPoint): String = Path("tessdata").absolutePathString()

}

fun main(args: Array<String>) {
    runApplication<OcrApplication>(*args)
}
