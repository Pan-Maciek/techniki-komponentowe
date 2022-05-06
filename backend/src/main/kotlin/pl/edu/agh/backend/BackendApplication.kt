package pl.edu.agh.backend

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.web.reactive.function.client.WebClient


@SpringBootApplication
class BackendApplication {
	@Bean
	fun webClient(builder: WebClient.Builder): WebClient = builder.build()

	@Bean
	@Scope("prototype")
	fun logger(injectionPoint: InjectionPoint): Logger = LoggerFactory.getLogger( injectionPoint.methodParameter?.containingClass ?: injectionPoint.field?.declaringClass) }


fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}
