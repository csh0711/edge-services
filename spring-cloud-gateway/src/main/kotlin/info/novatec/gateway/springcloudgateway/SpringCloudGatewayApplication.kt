package info.novatec.gateway.springcloudgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders.AUTHORIZATION


@SpringBootApplication
class SpringCloudGatewayApplication {

    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder, auhorizationFilterFactory: AuthorizationFilterFactory): RouteLocator =
            builder.routes {
                route(id = "comments") {
                    path("/comments")
                    uri("http://localhost:8082/comments")
                    filters {
                        //TODO: Check if token is present
                        cookie("my-token", """.+""")
                        filter(auhorizationFilterFactory.apply(null))
                    }
                }
                route(id = "users") {
                    path("/users")
                    uri("http://localhost:8081/users")
                }
            }

    @Bean
    fun auhorizationFilterFactory(): AuthorizationFilterFactory {
        return AuthorizationFilterFactory()
    }
}


fun main(args: Array<String>) {
    runApplication<SpringCloudGatewayApplication>(*args)
}

