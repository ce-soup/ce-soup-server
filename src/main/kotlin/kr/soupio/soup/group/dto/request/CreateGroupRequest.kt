package kr.soupio.soup.group.dto.request

import kr.soupio.soup.group.entities.DayOfTheWeek
import kr.soupio.soup.group.entities.GroupRecruitmentEnum
import kr.soupio.soup.group.entities.GroupScopeEnum
import kr.soupio.soup.group.entities.GroupTypeEnum
import org.springframework.web.multipart.MultipartFile

data class CreateGroupRequest(
    val image: MultipartFile?,
    val name: String,
    val content: String,
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
)