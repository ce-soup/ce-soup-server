package kr.soupio.soup.group.dto.request

import kr.soupio.soup.category.entities.Category
import kr.soupio.soup.file.entities.File
import kr.soupio.soup.group.entities.*
import kr.soupio.soup.member.entities.Member
import org.springframework.web.multipart.MultipartFile

data class CreateGroupRequest(
    val image: MultipartFile?,
    val name: String,
    val content: String,
    var manager: Member? = null,
    val type: GroupTypeEnum,
    val category: String?,
    val isOnline: Boolean,
    val scope: GroupScopeEnum,
    val recruitment: GroupRecruitmentEnum,
    val startHour: Int,
    var startMinute: Int,
    var endHour: Int? = null,
    var endMinute: Int? = null,
    var dayOfTheWeek: MutableList<DayOfTheWeek>? = ArrayList(),
    var meetingLink: String? = null,
) {
    fun toEntity(manager: Member, category: Category?, image: File?): Group {
        return Group(
            type = type,
            name = name,
            content = content,
            category = category,
            image = image,
            manager = manager,
            isOnline = isOnline,
            scope = scope,
            recruitment = recruitment,
            startHour = startHour,
            startMinute = startMinute,
            endHour = endHour,
            endMinute = endMinute,
            dayOfTheWeek = dayOfTheWeek,
            meetingLink = meetingLink
        )
    }
}