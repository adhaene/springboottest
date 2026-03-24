package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;
class SumTask extends RecursiveTask<Double> {
    private static final int THRESHOLD = 500; // Minimum size for parallel execution
    private final double[] data;
    private final int start, end;
    public SumTask(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Double compute() {
        if ((end - start) < THRESHOLD) {
            // Compute sequentially if the task size is below the threshold
            double sum = 0;
            for (int i = start; i < end; i++) {
                sum += data[i];
            }
            return sum;
        } else {
            // Split the task into two subtasks
            int middle = (start + end) / 2;
            SumTask subtask1 = new SumTask(data, start, middle);
            SumTask subtask2 = new SumTask(data, middle, end);
            // Fork the subtasks to execute them in parallel
            subtask1.fork();
            subtask2.fork();
            // Combine the results of the subtasks
            return subtask1.join() + subtask2.join();
        }
    }
}
public class RecursiveTaskExample {
    public static void main(String[] args) {
        double[] numbers = new double[5000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i % 2 == 0 ? i : -i;
        }
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(numbers, 0, numbers.length);
        double result = pool.invoke(task);
        System.out.println("Sum: " + result);

        ForkJoinTask<Double> taskResult = pool.submit(task);
        try {
            System.out.println("Sum: " + taskResult.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
