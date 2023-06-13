package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
    // GlobalFilter는 다른 CustomFilter보다 제일 첫번째로 실행되고 제일 마지막에 실행 됨

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Custon Pre Filter
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            log.info("Logging Filter baseMessage: {}",config.getBaseMessage());
//
//            if(config.isPreLogger()){
//                log.info("Logging Filter Start: request id -> {}", request.getId());
//            }
//
//            return chain.filter(exchange).then(Mono.fromRunnable(()->{
//                if(config.isPostLogger()){
//                    log.info("Logging Filter End: response code -> {}", response.getStatusCode());
//                }
//            }));
//        };
        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging Filter baseMessage: {}",config.getBaseMessage());

            if(config.isPreLogger()){
                log.info("Logging PRE Filter: request id -> {}", request.getId());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if(config.isPostLogger()){
                    log.info("Logging POST Filter: response code -> {}", response.getStatusCode());
                }
            }));
        },Ordered.LOWEST_PRECEDENCE);
        /*
        * Ordered.HIGHEST_PRECEDENCE
        * 이 필터의 실행 우선순위를 제일 높게 잡음 (Global보다 높음)
        *
        * Ordered.LOWEST_PRECEDENCE
        * 이 필터의 실행 우선순위를 제일 낮게 잡음
        * */

        return filter;
    }

    @Data
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
