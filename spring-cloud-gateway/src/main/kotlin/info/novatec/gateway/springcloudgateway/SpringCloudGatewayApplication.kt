package info.novatec.gateway.springcloudgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.tuple.TupleBuilder


@SpringBootApplication
class SpringCloudGatewayApplication {

    private val EMPTY_TUPLE = TupleBuilder.tuple().build()

    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder, authFilter: AuthorizationFilterFactory): RouteLocator =
            builder.routes {
                route(id = "users") {
                    path("/users")
                    uri("http://localhost:8081/users")
                    filters {
                        filter(authFilter.apply(EMPTY_TUPLE))
                    }
                }
                route(id = "comments") {
                    path("/comments")
                    uri("http://localhost:8082/comments")
                    filters {
                        filter(authFilter.apply(EMPTY_TUPLE))
                    }
                }
                route(id = "images") {
                    path("/images")
                    uri("http://localhost:8083/images")
                    filters {
                        filter(authFilter.apply(EMPTY_TUPLE))
                    }
                }
            }

    @Bean
    fun authorizationFilterFactory(): AuthorizationFilterFactory {
        return AuthorizationFilterFactory()
    }
}


fun main(args: Array<String>) {
    runApplication<SpringCloudGatewayApplication>(*args)
}

