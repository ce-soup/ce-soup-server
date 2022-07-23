package kr.soupio.soup.group

import kr.soupio.soup.group.dto.request.CreateGroupRequest
import kr.soupio.soup.group.dto.request.UpdateGroupRequest
import kr.soupio.soup.group.dto.response.GroupResponse
import kr.soupio.soup.group.entities.GroupRecruitmentEnum
import kr.soupio.soup.group.entities.GroupScopeEnum
import kr.soupio.soup.group.entities.GroupTypeEnum
import kr.soupio.soup.group.exception.NoGroupManagerAuthorityException
import kr.soupio.soup.group.services.GroupService
import kr.soupio.soup.member.dto.request.CreateMemberRequest
import kr.soupio.soup.member.entities.SexEnum
import kr.soupio.soup.member.service.MemberService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

    fun beforeCreateMember(): String? {
        return memberService.join(
            CreateMemberRequest(
                name = "member1",
                sex = SexEnum.Female
            )
        )
    }

    fun beforeCreateGroup(): GroupResponse? {
        val memberId: String? = beforeCreateMember()

        return groupService.create(
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
    }

    @Test
    @DisplayName("그룹을 생성할 수 있어요")
    fun createGroup() {
        val group: GroupResponse? = beforeCreateGroup()
        assertThat(group).isNotNull
    }

    @Test
    @DisplayName("그룹장은 그룹을 수정할 수 있어요")
    fun updateGroupWithManager() {
        val groupResponse: GroupResponse? = beforeCreateGroup()
        val memberId: String? = groupResponse?.manager?.id

        if (groupResponse != null) {
            val newGroup: GroupResponse = groupService.update(
                memberId!!,
                UpdateGroupRequest(
                    groupId = groupResponse.id,
                    image = null,
                    name = "자바 초급반",
                    content = "자바에 대해 간단히 배워봅시다.",
                    category = null,
                    isOnline = true,
                    scope = GroupScopeEnum.PUBLIC,
                    startHour = 13,
                    startMinute = 0,
                    endHour = 15,
                    endMinute = 0,
                    dayOfTheWeek = null,
                    meetingLink = null
                )
            )
            assertThat(newGroup.name).isEqualTo("자바 초급반")
        }
    }

    @Test
    @DisplayName("그룹장이 아닌 사람은 그룹을 수정할 수 없어요")
    fun updateGroupWithOrdinary() {
        val groupResponse: GroupResponse? = beforeCreateGroup()
        val memberId: String? = beforeCreateMember()

        if (groupResponse != null) {
            assertThrows<NoGroupManagerAuthorityException> {
                groupService.update(
                    memberId!!,
                    UpdateGroupRequest(
                        groupId = groupResponse.id,
                        image = null,
                        name = "자바 초급반",
                        content = "자바에 대해 간단히 배워봅시다.",
                        category = null,
                        isOnline = true,
                        scope = GroupScopeEnum.PUBLIC,
                        startHour = 13,
                        startMinute = 0,
                        endHour = 15,
                        endMinute = 0,
                        dayOfTheWeek = null,
                        meetingLink = null
                    )
                )
            }
        }
    }
}
