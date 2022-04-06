package pl.edu.agh.backend.search

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.io.File

@Service
class MicroserviceCommunicationService {

    val servicesNames : Map<String, String> = mapOf(
            "text-search" to "80",
            "odt-search" to "8182"
    )

    val restTemplate: RestTemplate = RestTemplateBuilder().build()

    fun getResponse(phrase : String, rootPath : String): Map<String, Any> {

        if(!fileExists(rootPath)){
            return mapOf("backend" to ErrorResponse(path = rootPath, errors = listOf("The given path does not exists.")) as Any)
        }

        val results = mutableMapOf<String, Any>()

        servicesNames.forEach {
            results.put(it.key, restTemplate.getForObject("http://${it.key}:${it.value}/search?phrase={phrase}&rootPath={path}", phrase, rootPath))
        }

        return results

    }

    fun fileExists(path: String) : Boolean = File(path).exists()

}