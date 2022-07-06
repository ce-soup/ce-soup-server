package kr.soupio.soup.member.service

import kr.soupio.soup.exception.NotExistMemberException
import kr.soupio.soup.file.entities.File
import kr.soupio.soup.file.entities.FileTypeEnum
import kr.soupio.soup.file.service.FileService
import kr.soupio.soup.member.dto.request.CreateMemberRequest
import kr.soupio.soup.member.dto.request.UpdateProfileRequest
import kr.soupio.soup.member.entities.Member
import kr.soupio.soup.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
    private val fileService: FileService,
) {

    @Transactional
    fun join(request: CreateMemberRequest): String? {
        val member = Member(
            name = request.name,
            sex = request.sex
        )
        memberRepository.save(member)
        return member.id
    }

    @Transactional
    fun uploadProfile(
        userId: String,
        request: UpdateProfileRequest
    ): Boolean {
        val member: Member = getMe(userId)

        if (member.profileImage != null)
            fileService.delete(member.profileImage!!)

        val file: File? = fileService.upload(FileTypeEnum.PROFILE, member, request.file)

        if (file != null) {
            member.changeProfileImage(file)
            return true
        }
        return false
    }

    fun getMe(userId: String): Member {
        return memberRepository.findById(userId)
            .orElseThrow { NotExistMemberException(userId) }
    }
}
