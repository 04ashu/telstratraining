package com.telstra.apigateway.tracing;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class TraceIdFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        String traceId = UUID.randomUUID().toString();

        return chain.filter(exchange.mutate()
                .request(exchange.getRequest()
                        .mutate()
                        .header("X-Trace-Id", traceId)
                        .build())
                .build());
    }
}
