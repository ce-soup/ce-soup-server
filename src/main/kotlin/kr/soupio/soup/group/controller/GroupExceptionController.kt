package kr.soupio.soup.group.controller

import kr.soupio.soup.exception.NotExistFileException
import kr.soupio.soup.group.exception.GroupNameAlreadyExistException
import kr.soupio.soup.group.exception.NoGroupManagerAuthorityException
import kr.soupio.soup.group.exception.NotExistGroupException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GroupExceptionController {

    @ExceptionHandler(value = [GroupNameAlreadyExistException::class])
    fun groupNameAlreadyExistException(exception: GroupNameAlreadyExistException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(value = [NotExistFileException::class])
    fun notExistFileException(exception: NotExistFileException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(value = [NoGroupManagerAuthorityException::class])
    fun noGroupManagerAuthorityException(exception: NoGroupManagerAuthorityException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.message)
    }

    @ExceptionHandler(value = [NotExistGroupException::class])
    fun notExistGroupException(exception: NotExistGroupException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }
}
