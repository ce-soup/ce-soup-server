package kr.soupio.soup.group.services

import kr.soupio.soup.category.repository.CategoryRepository
import kr.soupio.soup.file.entities.FileTypeEnum
import kr.soupio.soup.file.service.FileService
import kr.soupio.soup.group.dto.request.CreateGroupRequest
import kr.soupio.soup.group.dto.request.UpdateGroupRequest
import kr.soupio.soup.group.dto.response.GroupResponse
import kr.soupio.soup.group.entities.Group
import kr.soupio.soup.group.exception.GroupNameAlreadyExistException
import kr.soupio.soup.group.exception.NoGroupManagerAuthorityException
import kr.soupio.soup.group.exception.NotExistGroupException
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
    private val categoryRepository: CategoryRepository
) {

    @Transactional
    fun create(
        userId: String,
        request: CreateGroupRequest
    ): GroupResponse? {
        val member: Member = memberService.getMe(userId)
        if (groupRepository.findByName(request.name) != null)
            throw GroupNameAlreadyExistException()

        try {
            return GroupResponse(
                groupRepository.save(
                    Group(
                        type = request.type,
                        name = request.name,
                        content = request.content,
                        category = request.category?.let { categoryRepository.findByName(it) },
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
            )
        } catch (e: Exception) {
            return null
        }
    }

    @Transactional
    fun update(
        userId: String,
        request: UpdateGroupRequest
    ): GroupResponse {
        val member: Member = memberService.getMe(userId)
        val group: Group = getGroup(request.groupId)
        if (member != group.manager)
            throw NoGroupManagerAuthorityException()

        group.image = request.image?.let { fileService.upload(FileTypeEnum.GROUP, member, it) }
        group.name = request.name
        group.category = request.category?.let { categoryRepository.findByName(it) }
        group.content = request.content
        group.isOnline = request.isOnline
        group.scope = request.scope
        group.startHour = request.startHour
        group.startMinute = request.startMinute
        group.endHour = request.endHour
        group.endMinute = request.endMinute
        group.dayOfTheWeek = request.dayOfTheWeek
        group.meetingLink = request.meetingLink
        return GroupResponse(group)
    }

    fun get(groupId: String): GroupResponse {
        return GroupResponse(
            groupRepository.findById(groupId)
                .orElseThrow { NotExistGroupException(groupId) }
        )
    }

    fun delete(
        userId: String,
        groupId: String
    ): Boolean {
        return try {
            val member: Member = memberService.getMe(userId)
            val group: Group = getGroup(groupId)
            if (member != group.manager)
                throw NoGroupManagerAuthorityException()

            groupRepository.deleteById(groupId)
            true
        } catch (_: Exception) {
            false
        }
    }

    fun getGroup(groupId: String): Group {
        return groupRepository.findById(groupId)
            .orElseThrow { NotExistGroupException(groupId) }
    }
}
