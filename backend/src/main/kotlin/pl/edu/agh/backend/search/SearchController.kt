package pl.edu.agh.backend.search

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import kotlin.io.path.Path

@RestController
class SearchController @Autowired constructor(val communicationService : MicroserviceCommunicationService) {

    @CrossOrigin
    @GetMapping("/search")
    fun results(request : FrontendRequest) : Map<String, Any> {
        return communicationService.getResponse(request.phrase, request.rootPath)

    }

}
