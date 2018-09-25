package info.novatec.gateway.springcloudgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringCloudGatewayApplication {
    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder, authFilter: AuthorizationFilter): RouteLocator =
            builder.routes {
                route(id = "users") {
                    path("/users")
                    uri("http://localhost:8081/users")
                    filters {
                        filter(AuthorizationFilter())
                    }
                }
                route(id = "comments") {
                    path("/comments")
                    uri("http://localhost:8082/comments")
                    filters {
                        filter(AuthorizationFilter())
                    }
                }
                // The routing and filtering to '/images' is done via application.yml
                //route(id = "images") {
                //    path("/images")
                //    uri("http://localhost:8083/images")
                //    filters {
                //        filter(AuthorizationFilter())
                //    }
                //}
            }

    @Bean
    fun authorizationFilter(): AuthorizationFilter {
        return AuthorizationFilter()
    }
}


fun main(args: Array<String>) {
    runApplication<SpringCloudGatewayApplication>(*args)
}

