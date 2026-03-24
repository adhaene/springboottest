package com.bezkoder.spring.jpa.h2.thread;


/*
CompletableFuture is a class in Java that represents a future result of an asynchronous computation.
 It is part of the java.util.concurrent package and implements both the Future
 and CompletionStage interfaces.
 This allows it to be used for both managing the result of an asynchronous computation
  and chaining multiple asynchronous tasks.

 */
import java.util.concurrent.CompletableFuture;
public class CompletableFutureExample {
    public static void main(String[] args) {
        System.out.println("Start: ");
        // Create a CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Hello, World!";
        });
        // Attach a callback to be executed when the future completes
        future.thenAccept(result -> {
            System.out.println("Result: " + result);
        });
        // Wait for the future to complete
        future.join();
    }
}