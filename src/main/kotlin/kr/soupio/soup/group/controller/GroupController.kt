package kr.soupio.soup.group.controller

import kr.soupio.soup.group.dto.request.CreateGroupRequest
import kr.soupio.soup.group.services.GroupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/group")
class GroupController(
    private val groupService: GroupService
) {
    @PostMapping("/new")
    fun creatGroup(
        servletRequest: HttpServletRequest,
        request: CreateGroupRequest
    ): ResponseEntity<Boolean> {
        return ResponseEntity.ok().body(
            groupService.create(
                servletRequest.getAttribute("memberId") as String,
                request
            )
        )
    }
}