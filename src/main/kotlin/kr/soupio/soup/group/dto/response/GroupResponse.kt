package kr.soupio.soup.group.dto.response

import kr.soupio.soup.category.entities.Category
import kr.soupio.soup.group.entities.*
import kr.soupio.soup.member.entities.Member
import java.time.LocalDate


data class GroupResponse(
    var id: String,
    var name: String,
    var content: String,
    var image: String?,
    var type: GroupTypeEnum,
    var category: Category?,
    var manager: Member,
    var isOnline: Boolean,
    var scope: GroupScopeEnum,
    var recruitment: GroupRecruitmentEnum,
    var startDate: LocalDate? = null,
    var startHour: Int? = null,
    var startMinute: Int? = null,
    var endDate: LocalDate? = null,
    var endHour: Int? = null,
    var endMinute: Int? = null,
    var dayOfTheWeek: MutableList<DayOfTheWeek>? = ArrayList(),
    var personnel: Int = 0,
    var views: Int = 0,
    var status: GroupStatusEnum,
)

