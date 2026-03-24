package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ExecutionException;
import java.util.Random;

/*
How It Works
ForkJoinPool

Manages a pool of worker threads for parallel execution.
Uses a work-stealing algorithm to balance load.
RecursiveTask

Represents a task that returns a result.
Splits work into smaller subtasks until the workload is small enough to compute directly.
Threshold

Controls when to stop splitting and compute directly.
Too small → overhead; too large → less parallelism.
✅ Example Output (will vary due to random numbers):


Copy code
Sum of array: 494832
 */


// RecursiveTask to compute sum of an array in parallel
class ArraySumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 500; // Split size
    private final int[] array;
    private final int start;
    private final int end;

    public ArraySumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            // Compute directly if small enough
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Split into two subtasks
            int mid = start + length / 2;
            ArraySumTask leftTask = new ArraySumTask(array, start, mid);
            ArraySumTask rightTask = new ArraySumTask(array, mid, end);

            // Fork left task and compute right task in parallel
            leftTask.fork();
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}

public class ForkJoinExample {
    public static void main(String[] args) {
        // Create a large array with random integers
        int size = 10_000;
        int[] numbers = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt(100);
        }

        // Create ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        // Create the main task
        ArraySumTask task = new ArraySumTask(numbers, 0, numbers.length);

        try {
            // Submit task to pool and get result
            long result = pool.submit(task).get();
            System.out.println("Sum of array: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error during computation: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }
}
