package kr.soupio.soup.search.dto

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault

data class SearchRequest(
    @PageableDefault(page = 0, size = 10, sort = ["name"], direction = Sort.Direction.DESC)
    val pageable: Pageable,

    val keyword: String
)