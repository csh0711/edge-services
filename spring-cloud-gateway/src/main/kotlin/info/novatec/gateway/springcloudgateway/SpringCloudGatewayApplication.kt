package info.novatec.gateway.springcloudgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.filter.factory.GatewayFilters
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean


@SpringBootApplication
class SpringCloudGatewayApplication {

    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder, authorizationFilter: AuthorizationFilterFactory): RouteLocator =
            builder.routes {
                route(id = "users") {
                    path("/users")
                    uri("http://localhost:8081/users")
                }
                route(id = "comments") {
                    path("/comments")
                    uri("http://localhost:8082/comments")
                    filters {
                        filter(authorizationFilter.apply(GatewayFilters.EMPTY_TUPLE))
                    }
                }
                route(id = "images") {
                    path("/images")
                    uri("http://localhost:8083/images")
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

