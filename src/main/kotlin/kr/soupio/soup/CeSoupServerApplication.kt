package kr.soupio.soup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
class CeSoupServerApplication

fun main(args: Array<String>) {
    runApplication<CeSoupServerApplication>(*args)
}
