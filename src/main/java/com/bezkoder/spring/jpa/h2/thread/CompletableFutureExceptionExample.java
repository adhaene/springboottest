package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExceptionExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Example 1: Using exceptionally() to provide a fallback value
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Running task 1...");
            if (true) throw new RuntimeException("Something went wrong in task 1");
            return 10;
        }).exceptionally(ex -> {
            System.err.println("Handled in exceptionally(): " + ex.getMessage());
            return -1; // fallback value
        });

        System.out.println("future1 " + future1.get());

        // Example 2: Using handle() to process both result and exception
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Running task 2...");
            if (true) throw new RuntimeException("Something went wrong in task 2");
            return 20;
        }).handle((result, ex) -> {
            if (ex != null) {
                System.err.println("Handled in handle(): " + ex.getMessage());
                return -2; // fallback value
            }
            return result;
        });


            System.out.println("future2 " + future2.get());

        // Example 3 using whenComplete
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Running task 3...");
            if (true) throw new RuntimeException("Something went wrong in task 3");
            return 20;
        }).whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Handled in handle(): " + ex.getMessage());
            }
        });

        //System.out.println("future3" + future3.get());
    }

}