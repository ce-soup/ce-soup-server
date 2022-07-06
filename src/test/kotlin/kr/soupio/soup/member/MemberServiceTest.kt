package kr.soupio.soup.member

import kr.soupio.soup.member.dto.request.CreateMemberRequest
import kr.soupio.soup.member.entities.Member
import kr.soupio.soup.member.entities.SexEnum
import kr.soupio.soup.member.service.MemberService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MemberServiceTest {
    @Autowired
    private lateinit var memberService: MemberService

    @Test
    @DisplayName("새로운 회원을 생성할 수 있어요.")
    fun join() {
        val memberId: String? = memberService.join(
            CreateMemberRequest(
                name = "member1",
                sex = SexEnum.Female
            )
        )

        assertThat(memberId).isNotNull
    }

    @Test
    @DisplayName("가입한 회원은 찾을 수 있어요.")
    fun memberExistCheck() {
        val memberId: String? = memberService.join(
            CreateMemberRequest(
                name = "member1",
                sex = SexEnum.Female
            )
        )
        val resultId: Member = memberService.getMe(memberId!!)
        assertThat(memberId).isEqualTo(resultId.id)
    }
}
