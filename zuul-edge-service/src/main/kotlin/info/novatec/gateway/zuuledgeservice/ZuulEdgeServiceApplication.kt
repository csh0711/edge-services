package info.novatec.gateway.zuuledgeservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@SpringBootApplication
@EnableZuulProxy
class ZuulEdgeServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(ZuulEdgeServiceApplication::class.java, *args)
}
