package kr.soupio.soup.member.dto.request

import org.springframework.web.multipart.MultipartFile

data class UpdateProfileRequest(
    val file: MultipartFile,
    val bio: String
)