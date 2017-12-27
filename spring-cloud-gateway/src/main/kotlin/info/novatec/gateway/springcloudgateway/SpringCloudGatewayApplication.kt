package info.novatec.gateway.springcloudgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean


@SpringBootApplication
class SpringCloudGatewayApplication {

    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder): RouteLocator =
            builder.routes {
                route(id = "comments") {
                    path("/comments")
                    uri("http://localhost:8082/comments")
                }
                route(id = "users") {
                    path("/users")
                    uri("http://localhost:8081/users")
                }
            }
}

fun main(args: Array<String>) {
    runApplication<SpringCloudGatewayApplication>(*args)
}

