package info.novatec.gateway.usersservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class UsersServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(UsersServiceApplication::class.java, *args)
}
