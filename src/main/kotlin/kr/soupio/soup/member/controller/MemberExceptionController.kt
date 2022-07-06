package kr.soupio.soup.member.controller

import kr.soupio.soup.exception.NotExistMemberException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class MemberExceptionController {
    @ExceptionHandler(value = [NotExistMemberException::class])
    fun notFoundMemberException(exception: NotExistMemberException): ResponseEntity<String?>? {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.message)
    }
}
