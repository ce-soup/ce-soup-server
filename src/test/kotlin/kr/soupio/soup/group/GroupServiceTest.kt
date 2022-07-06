package kr.soupio.soup.group

import kr.soupio.soup.group.dto.request.CreateGroupRequest
import kr.soupio.soup.group.entities.GroupRecruitmentEnum
import kr.soupio.soup.group.entities.GroupScopeEnum
import kr.soupio.soup.group.entities.GroupTypeEnum
import kr.soupio.soup.group.services.GroupService
import kr.soupio.soup.member.dto.request.CreateMemberRequest
import kr.soupio.soup.member.entities.SexEnum
import kr.soupio.soup.member.service.MemberService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class GroupServiceTest {
    @Autowired
    private lateinit var groupService: GroupService

    @Autowired
    private lateinit var memberService: MemberService

    @Test
    @DisplayName("새로운 그룹을 생성할 수 있어요.")
    fun create() {
        val memberId: String? = memberService.join(
            CreateMemberRequest(
                name = "member1",
                sex = SexEnum.Female
            )
        )

        val group: Boolean = groupService.create(
            userId = memberId!!,
            CreateGroupRequest(
                type = GroupTypeEnum.STUDY,
                name = "코틀린 극복기",
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

        assertThat(group).isEqualTo(true)
    }
}
