package kr.soupio.soup.search.controller

import kr.soupio.soup.search.dto.SearchRequest
import kr.soupio.soup.search.dto.SearchResponse
import kr.soupio.soup.search.services.SearchService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/search")
class SearchController(
    private val searchService: SearchService
) {
    @GetMapping("/search")
    fun search(request: SearchRequest): ResponseEntity<SearchResponse> {
        return ResponseEntity.ok().body(searchService.searchGroupAndUser(request))
    }
}