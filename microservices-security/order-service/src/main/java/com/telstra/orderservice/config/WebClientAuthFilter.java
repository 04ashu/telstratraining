package com.telstra.orderservice.config;


import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;


@Component
public class WebClientAuthFilter {

    public ExchangeFilterFunction authFilter(){
        return (request, next) -> {


            ServletRequestAttributes attrs =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attrs != null) {
                String authHeader =
                        attrs.getRequest().getHeader(HttpHeaders.AUTHORIZATION);


                if (authHeader != null && !authHeader.isBlank()) {

                    ClientRequest newRequest = ClientRequest
                            .from(request)
                            .header(HttpHeaders.AUTHORIZATION, authHeader)
                            .build();

                    return next.exchange(newRequest);
                }
            }
            return next.exchange(request);
        };
    }
}
