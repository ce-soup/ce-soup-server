package kr.soupio.soup.member.service

import kr.soupio.soup.member.dto.request.CreateMemberRequest
import kr.soupio.soup.member.entities.Member
import kr.soupio.soup.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
) {

    @Transactional
    fun join(request: CreateMemberRequest): String? {
        val member = Member(request.name, request.sex)
        memberRepository.save(member)
        return member.id
    }

}