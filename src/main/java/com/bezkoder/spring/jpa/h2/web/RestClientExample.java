//package com.bezkoder.spring.jpa.h2.web;
//
//import org.springframework.web.client.RestClient;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//
///*
// RestClient (Spring 6+, Modern Synchronous API)
//Cleaner API than RestTemplate.
//Still blocking, but more fluent.
//Can use Apache HttpClient under the hood.
// */
//public class RestClientExample {
//    public static void main(String[] args) {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpComponentsClientHttpRequestFactory factory =
//                new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        RestClient restClient = RestClient.builder()
//                .requestFactory(factory)
//                .baseUrl("https://jsonplaceholder.typicode.com")
//                .build();
//
//        String body = restClient.get()
//                .uri("/posts/1")
//                .retrieve()
//                .body(String.class);
//
//        System.out.println(body);
//    }
//}
