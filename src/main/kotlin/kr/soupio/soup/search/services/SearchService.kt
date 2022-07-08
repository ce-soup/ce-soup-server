package kr.soupio.soup.search.services

import kr.soupio.soup.group.entities.Group
import kr.soupio.soup.group.repository.GroupRepository
import kr.soupio.soup.member.entities.Member
import kr.soupio.soup.member.repository.MemberRepository
import kr.soupio.soup.search.dto.SearchRequest
import kr.soupio.soup.search.dto.SearchResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SearchService(
    private val groupRepository: GroupRepository,
    private val memberRepository: MemberRepository,
) {

    fun searchGroupAndUser(request: SearchRequest): SearchResponse {
        val groupList: Page<Group> = groupRepository.findByNameContaining(
            keyword = request.keyword,
            pageable = request.pageable
        )
        val memberList: Page<Member> = memberRepository.findByNameContaining(
            keyword = request.keyword,
            pageable = request.pageable
        )
        return SearchResponse(groupList, memberList)
    }
}