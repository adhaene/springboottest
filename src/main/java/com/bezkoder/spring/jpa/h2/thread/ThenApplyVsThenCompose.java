package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenApplyVsThenCompose {

//    thenApply:
//
//    The function returns a CompletableFuture<String>, so the result is nested:
//    CompletableFuture<CompletableFuture<String>>.
//    You must call .get() twice (or .join() twice) to get the final value.
//
//    thenCompose:
//
//    The function returns a CompletableFuture<String>, but thenCompose flattens it into a single CompletableFuture<String>.
//    You only need one .get() or .join().

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Example using thenApply
        CompletableFuture<CompletableFuture<String>> nestedFuture =
                CompletableFuture.supplyAsync(() -> "Hello")
                        .thenApply(greeting -> getNameAsync(greeting)); // returns CompletableFuture<String>

        // Here we have a nested CompletableFuture<CompletableFuture<String>>
        System.out.println("thenApply result type: " + nestedFuture);

        System.out.println("thenApply final result: " + nestedFuture.get().get()); // Need two gets/join()

        System.out.println("------------------------------------------------");

        // Example using thenCompose
        CompletableFuture<String> flatFuture =
                CompletableFuture.supplyAsync(() -> "Hello")
                        .thenCompose(greeting -> getNameAsync(greeting)); // flattens automatically

        // Here we have a single CompletableFuture<String>
        System.out.println("thenCompose result type: " + flatFuture);
        System.out.println("thenCompose final result: " + flatFuture.get()); // Only one get/join()
    }

    // Simulates an async method that appends a name
    private static CompletableFuture<String> getNameAsync(String greeting) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.supplyAsync(() -> greeting + ", World!");
    }
}
