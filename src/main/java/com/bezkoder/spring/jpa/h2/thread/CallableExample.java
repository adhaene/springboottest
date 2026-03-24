package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.*;
public class CallableExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Integer> task = () -> {
            int sum = 0;
            for (int i = 1; i <= 5; i++) sum += i;
            return sum;
        };
        Future<Integer> future = executor.submit(task);
        System.out.println("Result: " + future.get()); // Blocks until result is ready
        executor.shutdown();
    }
}