//package com.bezkoder.spring.jpa.h2.web;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//
///*
//RestTemplate (Synchronous, Legacy)
//Blocking I/O.
//Still works but deprecated in favour of WebClient.
//Good for simple, synchronous calls.
// */
//public class RestTemplateExample {
//    public static void main(String[] args) {
//        // Create Apache HttpClient
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .disableCookieManagement()
//                .build();
//
//        // Wrap in Spring's request factory
//        HttpComponentsClientHttpRequestFactory requestFactory =
//                new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        RestTemplate restTemplate = new RestTemplate(requestFactory);
//
//        // Example GET request
//        ResponseEntity<String> response =
//                restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", String.class);
//
//        System.out.println("Status: " + response.getStatusCode());
//        System.out.println("Body: " + response.getBody());
//    }
//}
