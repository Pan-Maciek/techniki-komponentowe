package pl.edu.agh.backend.search

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriComponentsBuilder
import pl.edu.agh.backend.encode

@Service
class FormsService(
        @Autowired val webClient: WebClient,
        @Autowired val logger: Logger,
        @Autowired val createdForms: List<String>
){

    fun createForm(phrase: String, forms: List<String>): List<String>? {
        for (form in forms) {
            logger.info("Getting forms for $phrase in $form")
            val url = UriComponentsBuilder.fromHttpUrl("http://localhost:8188/${form}/search")
                .queryParam("phrase", phrase)
                .encode()
                .toUriString()

            val phrases = webClient.get().uri(url).retrieve().bodyToMono<List<String>>().block()
            logger.info("Forms result for $phrase in $form: $phrases")
            createdForms.plus(phrases)
        }
        return createdForms
    }
}