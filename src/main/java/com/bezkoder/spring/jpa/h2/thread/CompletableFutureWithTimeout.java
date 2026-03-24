package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.*;

public class CompletableFutureWithTimeout {

    // Simulated API calls
    private static String fetchUserProfile() {
        sleep(2);
        return "User Profile";
    }

    private static String fetchUserOrders() {
        sleep(5); // Simulate slow API
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
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            long start = System.currentTimeMillis();

            CompletableFuture<String> profileFuture =
                    CompletableFuture.supplyAsync(CompletableFutureWithTimeout::fetchUserProfile, executor)
                            .orTimeout(3, TimeUnit.SECONDS) // Timeout after 3s
                            .exceptionally(ex -> {
                                System.err.println("Profile fetch failed: " + ex.getMessage());
                                return "Default Profile";
                            });

            CompletableFuture<String> ordersFuture =
                    CompletableFuture.supplyAsync(CompletableFutureWithTimeout::fetchUserOrders, executor)
                            .orTimeout(3, TimeUnit.SECONDS) // Timeout after 3s
                            .exceptionally(ex -> {
                                System.err.println("Orders fetch failed: " + ex.getMessage());
                                return "Default Orders";
                            });

            CompletableFuture<String> recommendationsFuture =
                    CompletableFuture.supplyAsync(CompletableFutureWithTimeout::fetchRecommendations, executor)
                            .orTimeout(3, TimeUnit.SECONDS)
                            .exceptionally(ex -> {
                                System.err.println("Recommendations fetch failed: " + ex.getMessage());
                                return "Default Recommendations";
                            });

            // Wait for all tasks to complete
            CompletableFuture<Void> allTasks =
                    CompletableFuture.allOf(profileFuture, ordersFuture, recommendationsFuture);

            allTasks.join(); // Wait for all

            // Gather results
            String profile = profileFuture.get();
            String orders = ordersFuture.get();
            String recommendations = recommendationsFuture.get();

            long end = System.currentTimeMillis();

            System.out.println("\nFinal Results:");
            System.out.println(profile);
            System.out.println(orders);
            System.out.println(recommendations);
            System.out.println("Time taken: " + (end - start) / 1000.0 + " seconds");

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Unexpected error: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
