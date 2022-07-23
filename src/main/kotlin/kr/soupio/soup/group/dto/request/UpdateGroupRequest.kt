package kr.soupio.soup.group.dto.request

import kr.soupio.soup.group.entities.DayOfTheWeek
import kr.soupio.soup.group.entities.GroupScopeEnum
import org.springframework.web.multipart.MultipartFile

data class UpdateGroupRequest(
    val groupId: String,
    val image: MultipartFile?,
    val name: String,
    val content: String,
    val category: String?,
    val isOnline: Boolean,
    val scope: GroupScopeEnum,
    val startHour: Int,
    var startMinute: Int,
    var endHour: Int? = null,
    var endMinute: Int? = null,
    var dayOfTheWeek: MutableList<DayOfTheWeek>? = ArrayList(),
    var meetingLink: String? = null,
)
