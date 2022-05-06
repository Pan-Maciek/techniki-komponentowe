package pl.edu.agh.backend.search

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriComponentsBuilder
import java.io.File
import java.util.stream.Collectors

@Service
class MicroserviceCommunicationService(
        @Autowired val webClient: WebClient,
        @Autowired val translationService: TranslationService,
        @Autowired val logger: Logger
) {
    val serviceMap = ServiceMap()

    fun getResponse(phrase: String, rootPath: String, enabledFormats: List<String>, languages: List<String>): Map<String, Any> {
        logger.info("Searching for $phrase in $rootPath. Enabled formats: $enabledFormats; languages: $languages")

        if(!fileExists(rootPath)){
            logger.info("Requested path $rootPath does not exist")
            return mapOf("backend" to ErrorResponse(path = rootPath, errors = listOf("The given path does not exists.")) as Any)
        }

        val phrases = translationService.translate(phrase, languages)

        val activeServices = serviceMap.filterServices(enabledFormats)

        val monos = activeServices.map {
            val url = UriComponentsBuilder.fromHttpUrl("http://${it.key}:${it.value}/search")
                    .queryParam("lang", listOf("pl") + languages)
                    .queryParam("phrases", phrases)
                    .queryParam("rootPath", rootPath)
                    .encode()
                    .toUriString()

            it.key to webClient.get().uri(url).retrieve().bodyToMono<Any>()
        }.toMap()

        return monos.entries.parallelStream().map { entry ->
            logger.info("Sending request to ${entry.key} phrase: $phrase path: $rootPath")
            entry.key to
                    (entry.value
                            .onErrorReturn(ErrorResponse(rootPath, errors = listOf("Invalid request to service.")))
                            .block() ?: "[]")
        }.collect(Collectors.toMap({ it.first }, { it.second }))

    }

    fun fileExists(path: String) : Boolean = File(path).exists()

}