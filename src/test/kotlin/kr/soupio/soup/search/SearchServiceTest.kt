package kr.soupio.soup.search

import kr.soupio.soup.group.dto.request.CreateGroupRequest
import kr.soupio.soup.group.entities.GroupRecruitmentEnum
import kr.soupio.soup.group.entities.GroupScopeEnum
import kr.soupio.soup.group.entities.GroupTypeEnum
import kr.soupio.soup.group.services.GroupService
import kr.soupio.soup.member.dto.request.CreateMemberRequest
import kr.soupio.soup.member.entities.SexEnum
import kr.soupio.soup.member.service.MemberService
import kr.soupio.soup.search.dto.SearchRequest
import kr.soupio.soup.search.dto.SearchResponse
import kr.soupio.soup.search.services.SearchService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable

@SpringBootTest
class SearchServiceTest {

    @Autowired
    private lateinit var searchService: SearchService

    @Autowired
    private lateinit var memberService: MemberService

    @Autowired
    private lateinit var groupService: GroupService

    @Test
    @DisplayName("회원명과 그룹명을 검색할 수 있어요.")
    fun search() {
        val memberId: String? = memberService.join(
            CreateMemberRequest(
                name = "good",
                sex = SexEnum.Female
            )
        )

        val group: Boolean = groupService.create(
            userId = memberId!!,
            CreateGroupRequest(
                type = GroupTypeEnum.STUDY,
                name = "good11",
                content = "안녕하세요 코틀린 스터디입니다.",
                category = null,
                image = null,
                isOnline = false,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 0,
                endHour = 15,
                endMinute = 0,
                dayOfTheWeek = null,
                meetingLink = null
            )
        )

        val result: SearchResponse =
            searchService.searchGroupAndUser(SearchRequest(pageable = Pageable.ofSize(10), keyword = "good"))

        assertThat(result.groupList.totalElements).isEqualTo(1)
        assertThat(result.memberList.totalElements).isEqualTo(1)
    }
}