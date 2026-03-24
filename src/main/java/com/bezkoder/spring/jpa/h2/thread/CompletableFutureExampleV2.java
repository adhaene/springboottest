package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
Scenario: Fetching Data from Multiple APIs in Parallel
Imagine you have a service that needs to:

Fetch user profile from one API.
Fetch user orders from another API.
Fetch recommendations from a third API.
Instead of calling them sequentially (which is slow), you can use CompletableFuture
to run them in parallel and combine the results.

Why CompletableFuture is Useful Here
Parallel Execution: All API calls run at the same time, reducing total wait time.
Non-blocking: The main thread isn’t stuck waiting for each call sequentially.
Composability: You can easily combine, transform, or handle results asynchronously.
Error Handling: You can attach .exceptionally() or .handle() to gracefully manage failures.
💡 In this example, sequential execution would take 2 + 3 + 1 = 6 seconds,
but with CompletableFuture, it finishes in ~3 seconds (the longest single task).
 */
public class CompletableFutureExampleV2 {

    // Simulated API calls
    private static String fetchUserProfile() {
        sleep(2);
        return "User Profile";
    }

    private static String fetchUserOrders() {
        sleep(3);
        return "User Orders";
    }

    private static String fetchRecommendations() {
        sleep(1);
        return "Recommendations";
    }

    // Helper method to simulate delay
    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task interrupted", e);
        }
    }

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();

            // Run tasks in parallel
            CompletableFuture<String> profileFuture =
                    CompletableFuture.supplyAsync(CompletableFutureExampleV2::fetchUserProfile);

            CompletableFuture<String> ordersFuture =
                    CompletableFuture.supplyAsync(CompletableFutureExampleV2::fetchUserOrders);

            CompletableFuture<String> recommendationsFuture =
                    CompletableFuture.supplyAsync(CompletableFutureExampleV2::fetchRecommendations);

            // Combine all results
            CompletableFuture<Void> allTasks =
                    CompletableFuture.allOf(profileFuture, ordersFuture, recommendationsFuture);

            // Wait for all to complete and then gather results
            allTasks.join();

            String profile = profileFuture.get();
            String orders = ordersFuture.get();
            String recommendations = recommendationsFuture.get();

            long end = System.currentTimeMillis();

            System.out.println("Results:");
            System.out.println(profile);
            System.out.println(orders);
            System.out.println(recommendations);
            System.out.println("Time taken: " + (end - start) / 1000.0 + " seconds");

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
