package com.bezkoder.spring.jpa.h2.web;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

/*
HttpClient is created with a connection timeout.
HttpRequest is built with a per-request timeout.
The request is sent synchronously using client.send(...).
The program prints:
HTTP status code
Response headers
First 100 characters of the body (summary preview)
Includes robust error handling for:
Timeout
Invalid URL
I/O issues
Interrupted execution
 */
public class HttpClientSummaryExample {

    public static void main(String[] args) {
        // Example URL (replace with your target)
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        // Validate URL format
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            System.err.println("Invalid URL. Must start with http:// or https://");
            return;
        }

        // Create HttpClient with a global timeout
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        // Build GET request with per-request timeout
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        try {
            // Send request and get response as a String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print summary
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Headers: " + response.headers().map());
            String body = response.body();

            // Show only first 100 characters of body for summary
            String preview = body.length() > 100 ? body.substring(0, 100) + "..." : body;
            System.out.println("Body Preview: " + preview);

        } catch (HttpTimeoutException e) {
            System.err.println("Request timed out: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error occurred: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Request interrupted.");
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid URI: " + e.getMessage());
        }
    }
}

