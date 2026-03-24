package com.bezkoder.spring.jpa.h2.thread;


/*
CompletableFuture is a class in Java that represents a future result of an asynchronous computation.
 It is part of the java.util.concurrent package and implements both the Future
 and CompletionStage interfaces.
 This allows it to be used for both managing the result of an asynchronous computation
  and chaining multiple asynchronous tasks.

 */
import java.util.concurrent.*;

public class CompletableFutureExampleWithChain {
    public static void main(String[] args) {
        System.out.println("Start: ");
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,3,1, TimeUnit.HOURS,new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // Create a CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Hello, World!";
        }, poolExecutor);
        // Attach a callback to be executed when the future completes
        future.thenApply((String val) -> {
            //System.out.println("Result: " + val);
            return "Result: " + val;
        });
        // Wait for the future to complete
        String join = future.join();
        System.out.println("thenApply join: " + join);


        // Create a CompletableFuture
        future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Hello, World!";
        }, poolExecutor);
        // Attach a callback to be executed when the future completes
        future.thenCompose((String val) -> {
            //System.out.println("Result: " + val);
            return CompletableFuture.supplyAsync(() -> "Result: " + val);

                });
        // Wait for the future to complete
        join = future.join();
        System.out.println("thenCompose join: " + join);



        // Create a CompletableFuture
        CompletableFuture<Void> future3 = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Hello, World!";
        }, poolExecutor).thenAccept((String val) -> System.out.println("thenAccept join: " + val));
        // Wait for the future to complete
        Void join3 = future3.join();
        System.out.println("thenAccept join: " + join3);

    }
}