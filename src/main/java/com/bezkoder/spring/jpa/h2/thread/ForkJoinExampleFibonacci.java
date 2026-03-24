package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;
public class ForkJoinExampleFibonacci {
    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;
        Fibonacci(int n) { this.n = n; }
        @Override
        protected Integer compute() {
            if (n <= 1) return n;
            Fibonacci f1 = new Fibonacci(n - 1);
            Fibonacci f2 = new Fibonacci(n - 2);
            f1.fork(); // fork a new subtask
            return f2.compute() + f1.join(); // join the result of the forked task
        }
    }
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Fibonacci task = new Fibonacci(20);
        int result = pool.invoke(task);
        System.out.println("Fibonacci(10) = " + result);
    }
}
