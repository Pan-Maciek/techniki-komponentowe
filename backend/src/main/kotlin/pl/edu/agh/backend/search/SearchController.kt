package pl.edu.agh.backend.search

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(@Autowired val communicationService : MicroserviceCommunicationService) {

    @CrossOrigin
    @GetMapping("/search")
    fun results(request : FrontendRequest) : Map<String, Any> =
        communicationService.getResponse(request.phrase, request.rootPath, request.additionalInfo.enabledFormats, request.additionalInfo.lang)

}
