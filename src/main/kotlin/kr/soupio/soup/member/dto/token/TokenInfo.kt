package kr.soupio.soup.member.dto.token

import kotlinx.serialization.Serializable
import kr.soupio.soup.member.entities.RoleEnum

@Serializable
internal data class TokenInfo(
    val memberId: String,
    var roles: List<RoleEnum>,
)