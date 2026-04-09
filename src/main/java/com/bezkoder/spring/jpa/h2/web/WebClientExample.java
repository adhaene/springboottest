package com.bezkoder.spring.jpa.h2.web;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/*
WebClient (Reactive, Non-blocking)
Part of Spring WebFlux.
Supports async and streaming.
Recommended for high-concurrency apps.
 */
public class WebClientExample {
    public static void main(String[] args) {
        WebClient client = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        Mono<String> response = client.get()
                .uri("/posts/1")
                .retrieve()
                .bodyToMono(String.class);

        // Blocking here just for demo
        System.out.println(response.block());
    }
}
