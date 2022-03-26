package pl.edu.agh.backend.search

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class MicroserviceCommunicationService {

    val servicesNames : List<String> = listOf("text-search:80")

    val restTemplate: RestTemplate = RestTemplateBuilder().build()

    fun getResponse(phrase : String, rootPath : String): List<String?> {
        val results = mutableListOf<String>()

        servicesNames.forEach {
            results.add(restTemplate.getForObject("http://$it/search?phrase={phrase}&rootPath={path}", phrase, rootPath))
        }

        return results

    }

}