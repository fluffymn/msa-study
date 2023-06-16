package com.example.apigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApigatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApigatewayServiceApplication.class, args);
    }

    @Bean
    public HttpTraceRepository httpTraceRepository() {
        // 클라이언트가 `/httptrace`요청하는`` trace 정보가 메모리에 담겨서 제공하게 됨
        return new InMemoryHttpTraceRepository();
    }
}
