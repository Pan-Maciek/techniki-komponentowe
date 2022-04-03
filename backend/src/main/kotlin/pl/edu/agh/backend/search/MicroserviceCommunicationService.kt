package pl.edu.agh.backend.search

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class MicroserviceCommunicationService {

    val servicesNames : Map<String, String> = mapOf(
            "text-search" to "80",
            "odt-search" to "8182"
    )

    val restTemplate: RestTemplate = RestTemplateBuilder().build()

    fun getResponse(phrase : String, rootPath : String): Map<String, Object?> {
        val results = mutableMapOf<String, Object>()

        servicesNames.forEach {
            results.put(it.key, restTemplate.getForObject("http://${it.key}:${it.value}/search?phrase={phrase}&rootPath={path}", phrase, rootPath))
        }

        return results

    }

}