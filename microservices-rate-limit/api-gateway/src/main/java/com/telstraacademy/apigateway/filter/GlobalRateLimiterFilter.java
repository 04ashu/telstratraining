package com.telstraacademy.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GlobalRateLimiterFilter implements GlobalFilter, Ordered {

    private static final int MAX_REQUESTS = 5;
    private static final long WINDOW_SIZE = 10 * 1000;  //10 sec

    private final Map<String, RequestCounter> cache = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String ip = exchange.getRequest()
                .getRemoteAddress()
                .getAddress()
                .getHostAddress();

        long now = System.currentTimeMillis();

        RequestCounter counter = cache.computeIfAbsent(ip,k->new RequestCounter(0,now));

        synchronized (counter){
            if (now - counter.windowStart > WINDOW_SIZE){
                counter.reset(now);
            }

            if (counter.count < MAX_REQUESTS) {
                counter.increment();
                return chain.filter(exchange);
            }
        }

        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        DataBuffer buffer = exchange.getResponse()
                .bufferFactory()
                .wrap("Rate limit exceeded".getBytes());

        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return -1;
    }

    static class RequestCounter{
        int count;
        long windowStart;

        RequestCounter(int count, long windowStart){
            this.count = count;
            this.windowStart = windowStart;
        }

        void increment(){
            count++;
        }

        void reset(long now){
            count = 1;
            windowStart = now;
        }
    }
}
