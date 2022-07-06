package kr.soupio.soup.search.services

import kr.soupio.soup.group.entities.Group
import kr.soupio.soup.group.services.GroupService
import kr.soupio.soup.member.entities.Member
import kr.soupio.soup.member.service.MemberService
import kr.soupio.soup.search.dto.SearchRequest
import kr.soupio.soup.search.dto.SearchResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SearchService(
    private val groupService: GroupService,
    private val memberService: MemberService
) {

    fun searchGroupAndUser(request: SearchRequest): SearchResponse {
        val groupList: Page<Group> = groupService.searchGroup(request)
        val memberList: Page<Member> = memberService.searchMember(request)

        return SearchResponse(groupList, memberList)
    }
}