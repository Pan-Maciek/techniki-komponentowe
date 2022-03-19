package pl.edu.agh.backend.search

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController {

    @CrossOrigin
    @GetMapping("/search")
    fun results(request : FrontendRequest) : List<PathItem> {
        return listOf(PathItem(request.rootPath))
    }

}
