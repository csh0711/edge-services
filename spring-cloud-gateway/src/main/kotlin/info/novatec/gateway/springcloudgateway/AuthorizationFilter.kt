package info.novatec.gateway.springcloudgateway

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class AuthorizationFilter : GatewayFilter {

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val cookie = exchange.request.cookies.getFirst("customer-Id")
        if (cookie?.value.isNullOrEmpty()) {
            exchange.response.statusCode = HttpStatus.BAD_REQUEST
           return exchange.response.setComplete()
        } else {
            val request = exchange.request
                    .mutate()
                    .header(HttpHeaders.AUTHORIZATION, cookie.value)
                    .build()
            return chain.filter(exchange.mutate().request(request).build())
        }
    }

}