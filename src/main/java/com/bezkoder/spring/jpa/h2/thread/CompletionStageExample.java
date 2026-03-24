package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
Here’s a concise summary of CompletionStage in Java, along with a runnable example
using CompletableFuture (which implements CompletionStage).

Summary
CompletionStage<T>: An interface representing a stage of an asynchronous computation.
Key Features:
Supports chaining of dependent actions (thenApply, thenAccept, thenRun).
Supports asynchronous execution (thenApplyAsync, etc.).
Can combine multiple stages (thenCombine, allOf, anyOf).
Handles exceptions (exceptionally, handle).
Implementation: Most commonly used via CompletableFuture.
 */
public class CompletionStageExample {
    public static void main(String[] args) {
        try {
            // Stage 1: Supply an initial value asynchronously
            CompletableFuture<Integer> stage1 = CompletableFuture.supplyAsync(() -> {
                System.out.println("Stage 1: Generating number...");
                return 10;
            });

            // Stage 2: Transform the result
            CompletableFuture<Integer> stage2 = stage1.thenApply(num -> {
                System.out.println("Stage 2: Doubling the number...");
                return num * 2;
            });

            // Stage 3: Combine with another async computation
            CompletableFuture<Integer> stage3 = stage2.thenCombine(
                    CompletableFuture.supplyAsync(() -> {
                        System.out.println("Stage 3: Generating another number...");
                        return 5;
                    }),
                    (result1, result2) -> {
                        System.out.println("Combining results...");
                        return result1 + result2;
                    }
            );

            // Stage 4: Handle exceptions
            CompletableFuture<Integer> finalStage = stage3.exceptionally(ex -> {
                System.err.println("Error occurred: " + ex.getMessage());
                return 0;
            });

            // Get the final result
            System.out.println("Final Result: " + finalStage.get());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
