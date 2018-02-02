package info.novatec.gateway.imagesservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ImagesServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(ImagesServiceApplication::class.java, *args)
}
