package kr.soupio.soup.member.controller

import kr.soupio.soup.member.dto.request.UpdateProfileRequest
import kr.soupio.soup.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService,
) {
    @PostMapping("/profile")
    fun uploadProfile(
        servletRequest: HttpServletRequest,
        request: UpdateProfileRequest
    ): ResponseEntity<Boolean> {
        return ResponseEntity.ok().body(
            memberService.uploadProfile(
                servletRequest.getAttribute("memberId") as String, request
            )
        )
    }
}
