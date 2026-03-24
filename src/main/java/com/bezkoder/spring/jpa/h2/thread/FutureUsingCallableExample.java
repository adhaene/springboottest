package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureUsingCallableExample {
    public static void main(String[] args) {
        // Create a thread pool with a single worker thread
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Define a task that returns a result after some delay
        Callable<String> task = () -> {
            try {
                System.out.println("Task started...");
                TimeUnit.SECONDS.sleep(2); // Simulate long-running work
                return "Task completed successfully!";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                return "Task was interrupted!";
            }
        };

        // Submit the task and get a Future object
        Future<String> future = executor.submit(task);

        // Do other work while the task runs
        System.out.println("Main thread is free to do other work...");

        try {
            // Check if the task is done
            while (!future.isDone()) {
                System.out.println("Task is still running...");
                TimeUnit.MILLISECONDS.sleep(500);
            }

            // Retrieve the result (blocks if not ready)
            String result = future.get();
            System.out.println("Result: " + result);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main thread interrupted.");
        } catch (ExecutionException e) {
            System.err.println("Error during task execution: " + e.getCause());
        } finally {
            // Shut down the executor
            executor.shutdown();
        }
    }
}
