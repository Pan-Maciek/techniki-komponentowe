package pl.edu.agh.backend.search

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.io.File

@Service
class MicroserviceCommunicationService(
    @Autowired val restTemplate: RestTemplate,
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

        return serviceMap.filterServices(enabledFormats).mapValues {
            val serviceUrl ="http://${it.key}:${it.value}/search?phrases={phrases}&rootPath={path}"
            logger.info("Sending request to ${it.key}", phrase, rootPath)
            restTemplate.getForObject(serviceUrl, phrases, rootPath)
        }
    }

    fun fileExists(path: String) : Boolean = File(path).exists()

}