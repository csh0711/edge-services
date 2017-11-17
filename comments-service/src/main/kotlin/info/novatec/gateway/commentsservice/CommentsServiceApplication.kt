package info.novatec.gateway.commentsservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CommentsServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(CommentsServiceApplication::class.java, *args)
}
