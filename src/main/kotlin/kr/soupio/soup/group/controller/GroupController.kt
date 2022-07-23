package kr.soupio.soup.group.controller

import kr.soupio.soup.group.dto.request.CreateGroupRequest
import kr.soupio.soup.group.dto.request.ListGroupRequest
import kr.soupio.soup.group.dto.request.UpdateGroupRequest
import kr.soupio.soup.group.dto.response.GroupResponse
import kr.soupio.soup.group.services.GroupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/group")
class GroupController(
    private val groupService: GroupService
) {
    @PostMapping("/new")
    fun creatGroup(
        servletRequest: HttpServletRequest,
        @RequestBody request: CreateGroupRequest
    ): ResponseEntity<GroupResponse> {
        return ResponseEntity.ok().body(
            groupService.create(
                servletRequest.getAttribute("memberId") as String,
                request
            )
        )
    }

    @GetMapping("/{groupId}")
    fun getGroup(
        @PathVariable("groupId") groupId: String,
    ): ResponseEntity<GroupResponse> {
        return ResponseEntity.ok().body(
            groupService.get(
                groupId
            )
        )
    }

    @PatchMapping("/update")
    fun updateGroup(
        servletRequest: HttpServletRequest,
        @RequestBody request: UpdateGroupRequest
    ): ResponseEntity<GroupResponse> {
        return ResponseEntity.ok().body(
            groupService.update(
                servletRequest.getAttribute("memberId") as String,
                request
            )
        )
    }

    @DeleteMapping("/{groupId}")
    fun deleteGroup(
        servletRequest: HttpServletRequest,
        @PathVariable("groupId") groupId: String,
    ): ResponseEntity<Boolean> {
        return ResponseEntity.ok().body(
            groupService.delete(
                servletRequest.getAttribute("memberId") as String,
                groupId
            )
        )
    }

    @GetMapping("/list/all")
    fun list(
        @RequestBody request: ListGroupRequest
    ) {

    }

}
