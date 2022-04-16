package pl.edu.agh.backend.search

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class TranslationService(
    @Autowired val restTemplate: RestTemplate,
    @Autowired val logger: Logger
){

    fun translate(phrase: String, languages: List<String>): List<String> {
        logger.info("Getting translations for $phrase in $languages")
        val url = "http://localhost:3000/translate?phrase={phrase}&lang={languages}"
        return restTemplate.getForObject(url, phrase, languages)
    }

    fun languages(): Map<String, String> {
        logger.info("Getting language list")
        val url = "http://localhost:3000/languages"
        return restTemplate.getForObject(url)
    }
}