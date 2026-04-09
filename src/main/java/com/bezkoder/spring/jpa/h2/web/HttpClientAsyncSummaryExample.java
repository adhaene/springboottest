package com.bezkoder.spring.jpa.h2.web;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
Key Differences from the Synchronous Version
Non-blocking: Uses sendAsync() which returns a CompletableFuture.
thenAccept(): Processes the response when it arrives.
exceptionally(): Handles errors without stopping the program.
futureResponse.get() at the end ensures the program doesn’t exit
before the async call finishes (you could remove this in a real async app like a server).
 */
public class HttpClientAsyncSummaryExample {

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

        // Send request asynchronously
        CompletableFuture<HttpResponse<String>> futureResponse =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // Process the response asynchronously
        futureResponse.thenAccept(response -> {
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Headers: " + response.headers().map());

            String body = response.body();
            String preview = body.length() > 100 ? body.substring(0, 100) + "..." : body;
            System.out.println("Body Preview: " + preview);
        }).exceptionally(ex -> {
            // Handle exceptions
            if (ex.getCause() instanceof HttpTimeoutException) {
                System.err.println("Request timed out: " + ex.getMessage());
            } else if (ex.getCause() instanceof IOException) {
                System.err.println("I/O error occurred: " + ex.getMessage());
            } else {
                System.err.println("Error: " + ex.getMessage());
            }
            return null;
        });

        // Keep main thread alive until async task completes
        try {
            futureResponse.get(); // Wait for completion
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main thread interrupted.");
        } catch (ExecutionException e) {
            System.err.println("Execution error: " + e.getMessage());
        }
    }
}
