package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAllOfExample {

    public static void main(String[] args) {
        // Create multiple asynchronous tasks
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            simulateWork("Task 1", 2);
            return "Result from Task 1";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            simulateWork("Task 2", 3);
            return "Result from Task 2";
        });

        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            simulateWork("Task 3", 1);
            return "Result from Task 3";
        });

        // Combine all tasks using allOf
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);

        try {
            // Wait for all tasks to complete
            allTasks.join(); // join() throws unchecked exceptions, unlike get()

            // Retrieve results after all tasks are done
            String result1 = task1.get();
            String result2 = task2.get();
            String result3 = task3.get();

            System.out.println("All tasks completed successfully!");
            System.out.println(result1);
            System.out.println(result2);
            System.out.println(result3);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            System.err.println("Execution was interrupted.");
        } catch (ExecutionException e) {
            System.err.println("An error occurred in one of the tasks: " + e.getCause());
        }
    }

    // Simulates work with a delay
    private static void simulateWork(String taskName, int seconds) {
        try {
            System.out.println(taskName + " started...");
            TimeUnit.SECONDS.sleep(seconds);
            System.out.println(taskName + " finished.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(taskName + " was interrupted.");
        }
    }
}
