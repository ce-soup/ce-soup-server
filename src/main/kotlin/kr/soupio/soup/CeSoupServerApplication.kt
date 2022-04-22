package kr.soupio.soup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CeSoupServerApplication

fun main(args: Array<String>) {
    runApplication<CeSoupServerApplication>(*args)
}
