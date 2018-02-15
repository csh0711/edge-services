package info.novatec.gateway.springcloudgateway

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.tuple.Tuple

class AuthorizationFilterFactory : GatewayFilterFactory {

    override fun apply(args: Tuple): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val cookie = exchange.request.cookies.getFirst("customer-Id")
            if (cookie?.value.isNullOrEmpty()) {
                exchange.response.statusCode = HttpStatus.BAD_REQUEST
                exchange.response.setComplete()
            } else {
                val request = exchange.request
                        .mutate()
                        .header(HttpHeaders.AUTHORIZATION, cookie.value)
                        .build()
                chain.filter(exchange.mutate().request(request).build())
            }
        }
    }
}