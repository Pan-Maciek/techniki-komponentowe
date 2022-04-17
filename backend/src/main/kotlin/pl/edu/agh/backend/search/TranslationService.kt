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
        val url = "http://translation:3000/translate?phrase={phrase}&lang={languages}"
        val phrases = restTemplate.getForObject<List<String>>(url, phrase, languages)
        logger.info("Translation result for $phrase in $languages: $phrases")
        return phrases
    }

    fun languages(): Map<String, String> {
        logger.info("Getting language list")
        val url = "http://translation:3000/languages"
        return restTemplate.getForObject(url)
    }
}