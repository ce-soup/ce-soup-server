package kr.soupio.soup.search.dto

import kr.soupio.soup.group.entities.Group
import kr.soupio.soup.member.entities.Member
import org.springframework.data.domain.Page

data class SearchResponse(
    val groupList: Page<Group>,
    val memberList: Page<Member>
)
