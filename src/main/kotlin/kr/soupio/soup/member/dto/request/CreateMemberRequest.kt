package kr.soupio.soup.member.dto.request

import kr.soupio.soup.member.entities.SexEnum

data class CreateMemberRequest(
    val name: String,
    val sex: SexEnum,
)
