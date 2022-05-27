package pl.edu.agh.backend.search

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.UriComponentsBuilder

@Service
class FormsService(
        @Autowired val webClient: WebClient,
        @Autowired val logger: Logger,
){

    fun createForm(phrase: String, forms: List<String>): List<String>? {
        val createdForms: MutableList<String> = mutableListOf()
        for (form in forms) {
//            logger.info("Getting forms for $phrase in $form")
            val url = UriComponentsBuilder.fromHttpUrl("http://words-forms:8187/${form}/search")
                .queryParam("phrases", phrase)
                .encode()
                .toUriString()

            val phrases = webClient.get().uri(url).retrieve().bodyToMono<List<String>>().block()
//            logger.info("Forms result for $phrase in $form: $phrases")
            if (phrases != null) {
                for (phr in phrases) {
                    createdForms.add(phr)
                }
            }
        }
        logger.info("All prepared forms for $phrase in $forms: $createdForms")
        return createdForms
    }
}