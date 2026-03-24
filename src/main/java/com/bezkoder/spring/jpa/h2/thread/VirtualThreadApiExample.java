package com.bezkoder.spring.jpa.h2.thread;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.Executors;

/*
Got it — here’s a practical “use case” example of Java Project Loom virtual threads in action.
We’ll simulate making multiple API calls in parallel without the complexity of callbacks or reactive frameworks.

Example: Fetching Data from Multiple APIs Concurrently

Why this works well with Virtual Threads

Each HTTP request blocks while waiting for a response, but virtual threads make blocking cheap.
You can scale to thousands of concurrent requests without exhausting OS threads.
Code stays simple and synchronous — no reactive complexity.
If you want, I can also show you a structured concurrency version of this that automatically
cancels all tasks if one fails — a big advantage of Project Loom.

 */
public class VirtualThreadApiExample {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        List<String> urls = List.of(
                "https://api.github.com",
                "https://httpbin.org/delay/2",
                "https://jsonplaceholder.typicode.com/posts/1"
        );

//        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
//            var futures = urls.stream()
//                    .map(url -> executor.submit(() -> {
//                        HttpRequest request = HttpRequest.newBuilder()
//                                .uri(URI.create(url))
//                                .build();
//                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//                        System.out.println("Fetched from " + url + " | Status: " + response.statusCode());
//                        return response.body();
//                    }))
//                    .toList();
//
//            // Wait for all tasks to complete
//            for (var future : futures) {
//                future.get();
//            }
//        }
    }
}
