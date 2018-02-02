package info.novatec.gateway.springcloudgateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.tuple.Tuple;

public class AuthorizationFilterFactory implements GatewayFilterFactory {

    @Override
    public GatewayFilter apply(Tuple args) {
        return (exchange, chain) -> {
            HttpCookie cookie = exchange.getRequest().getCookies().getFirst("customer-Id");
            if (cookie == null || cookie.getValue().isEmpty()) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                response.setComplete();
                return chain.filter(exchange.mutate().response(response).build());
            }
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header(HttpHeaders.AUTHORIZATION, cookie.getValue())
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}

