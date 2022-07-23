package kr.soupio.soup.group.dto.request

import kr.soupio.soup.group.entities.GroupStatusEnum
import kr.soupio.soup.group.entities.GroupTypeEnum

data class ListGroupRequest(
    val type: GroupTypeEnum,
    val state: GroupStatusEnum? = null,
    val isOnline: Boolean? = null,
    val minPersonnel: Int? = null,
    val maxPersonnel: Int? = null
)
