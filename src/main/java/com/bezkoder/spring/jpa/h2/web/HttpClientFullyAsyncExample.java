package com.bezkoder.spring.jpa.h2.web;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class HttpClientFullyAsyncExample {

    public static void main(String[] args) {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        // Validate URL format
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            System.err.println("Invalid URL. Must start with http:// or https://");
            return;
        }

        // Create HttpClient with a global connection timeout
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        // Build GET request with per-request timeout
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        // Send request asynchronously without blocking
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    // Process and return a summary string
                    String body = response.body();
                    String preview = body.length() > 100 ? body.substring(0, 100) + "..." : body;
                    return String.format(
                            "Status Code: %d%nHeaders: %s%nBody Preview: %s",
                            response.statusCode(),
                            response.headers().map(),
                            preview
                    );
                })
                .thenAccept(summary -> {
                    // Print the summary
                    System.out.println(summary);
                })
                .exceptionally(ex -> {
                    // Handle exceptions gracefully
                    Throwable cause = ex.getCause();
                    if (cause instanceof HttpTimeoutException) {
                        System.err.println("Request timed out: " + cause.getMessage());
                    } else if (cause instanceof IOException) {
                        System.err.println("I/O error occurred: " + cause.getMessage());
                    } else {
                        System.err.println("Error: " + ex.getMessage());
                    }
                    return null;
                });

        // Keep the program alive long enough for async work to finish
        // In a real app (server, GUI, etc.), the event loop would keep it alive automatically
        try {
            Thread.sleep(3000); // Adjust if needed for slow networks
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
