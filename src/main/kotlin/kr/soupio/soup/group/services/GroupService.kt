package kr.soupio.soup.group.services

import kr.soupio.soup.category.service.CategoryService
import kr.soupio.soup.file.entities.FileTypeEnum
import kr.soupio.soup.file.service.FileService
import kr.soupio.soup.group.dto.request.CreateGroupRequest
import kr.soupio.soup.group.entities.Group
import kr.soupio.soup.group.exception.GroupNameAlreadyExistException
import kr.soupio.soup.group.repository.GroupRepository
import kr.soupio.soup.member.entities.Member
import kr.soupio.soup.member.service.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GroupService(
    private val groupRepository: GroupRepository,
    private val memberService: MemberService,
    private val fileService: FileService,
    private val categoryService: CategoryService
) {

    @Transactional
    fun create(
        userId: String,
        request: CreateGroupRequest
    ): Boolean {
        val member: Member = memberService.getMe(userId)
        if (groupRepository.findByName(request.name) != null)
            throw GroupNameAlreadyExistException()

        try {
            groupRepository.save(
                Group(
                    type = request.type,
                    name = request.name,
                    content = request.content,
                    category = request.category?.let { categoryService.findByName(it) },
                    image = request.image?.let { fileService.upload(FileTypeEnum.GROUP, member, it) },
                    manager = member,
                    isOnline = request.isOnline,
                    scope = request.scope,
                    recruitment = request.recruitment,
                    startHour = request.startHour,
                    startMinute = request.startMinute,
                    endHour = request.endHour,
                    endMinute = request.endMinute,
                    dayOfTheWeek = request.dayOfTheWeek,
                    meetingLink = request.meetingLink
                )
            )
            return true
        } catch (e: Exception) {
            return false
        }
    }
}
