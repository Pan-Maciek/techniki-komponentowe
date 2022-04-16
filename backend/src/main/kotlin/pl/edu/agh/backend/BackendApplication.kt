package pl.edu.agh.backend

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate


@SpringBootApplication
class BackendApplication {
	@Bean
	fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()

	@Bean
	fun logger(injectionPoint: InjectionPoint): Logger = LoggerFactory.getLogger( injectionPoint.methodParameter?.containingClass ?: injectionPoint.field?.declaringClass) }


fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}
