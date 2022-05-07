package pl.edu.agh.backend.search

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriComponentsBuilder
import pl.edu.agh.backend.encode

@Service
class TranslationService(
        @Autowired val webClient: WebClient,
        @Autowired val logger: Logger
){

    fun translate(phrase: String, languages: List<String>): List<String>? {
        logger.info("Getting translations for $phrase in $languages")
        val url = UriComponentsBuilder.fromHttpUrl("http://translation:3000/translate")
                .queryParam("phrase", phrase.encode())
                .queryParam("lang", languages)
                .encode()
                .toUriString()

        val phrases = webClient.get().uri(url).retrieve().bodyToMono<List<String>>().block()
        logger.info("Translation result for $phrase in $languages: $phrases")
        return phrases
    }

    fun languages(): Map<String, String>? {
        logger.info("Getting language list")
        val url = "http://translation:3000/languages"
        return webClient.get().uri(url).retrieve().bodyToMono<Map<String, String>>().block()
    }
}