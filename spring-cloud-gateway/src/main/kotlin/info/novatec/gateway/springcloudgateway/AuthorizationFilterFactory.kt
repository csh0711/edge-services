package info.novatec.gateway.springcloudgateway

import info.novatec.gateway.springcloudgateway.AuthorizationFilterFactory.Config
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

/**
 * Filter that extracts the `customer-Id` cookie and adds it's value to the
 * request's Authorization header.
 *
 * Note: this filter is used for the `/images` route which is defined in
 * the application.yml.
 */
@Component
class AuthorizationFilterFactory : AbstractGatewayFilterFactory<Config>(Config::class.java) {

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val cookie = exchange.request.cookies.getFirst("customer-Id")
            if (cookie?.value.isNullOrEmpty()) {
                exchange.response.statusCode = HttpStatus.BAD_REQUEST
                exchange.response.setComplete()
            } else {
                val request = exchange.request
                        .mutate()
                        .header(HttpHeaders.AUTHORIZATION, cookie!!.value)
                        .build()
                chain.filter(exchange.mutate().request(request).build())
            }
        }
    }

    class Config
}
