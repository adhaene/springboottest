package com.bezkoder.spring.jpa.h2.lamdba;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

public class LambdaExample {
    public static void main(String[] args) {
        // Example 1: Lambda for a custom functional interface
        MathOperation add = (a, b) -> a + b;
        MathOperation multiply = (a, b) -> a * b;

        System.out.println("Addition: " + add.operate(5, 3));
        System.out.println("Multiplication: " + multiply.operate(5, 3));

        // Example 2: Lambda with built-in functional interface (Consumer)
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("Hello, " + name));

        // Example 3: Lambda with Streams
        long count = names.stream()
                .filter(name -> name.startsWith("A"))
                .count();
        System.out.println("Names starting with A: " + count);
    }
}
