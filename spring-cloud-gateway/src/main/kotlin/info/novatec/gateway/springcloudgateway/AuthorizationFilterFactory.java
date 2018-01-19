package info.novatec.gateway.springcloudgateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.tuple.Tuple;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AuthorizationFilterFactory implements GatewayFilterFactory {
    @Override
    public GatewayFilter apply(Tuple args) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getCookies().get("my-token").get(0).getValue();
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header(AUTHORIZATION, token)
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}
