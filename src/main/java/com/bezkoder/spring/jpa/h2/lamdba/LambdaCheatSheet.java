package com.bezkoder.spring.jpa.h2.lamdba;

import java.util.*;
import java.util.function.*;

/*
2. Common Functional Interfaces (java.util.function)
Interface	Method Signature	Example Lambda
Predicate<T>	boolean test(T t)	x -> x > 10
Function<T,R>	R apply(T t)	x -> x * 2
Consumer<T>	void accept(T t)	x -> System.out.println(x)
Supplier<T>	T get()	() -> Math.random()
BiFunction<T,U,R>	R apply(T t, U u)	(a, b) -> a + b
UnaryOperator<T>	T apply(T t)	x -> x.toUpperCase()
BinaryOperator<T>	T apply(T t1, T t2)	(a, b) -> a * b
 */
public class LambdaCheatSheet {
    public static void main(String[] args) {
        // Predicate: test a condition
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));

        // Function: transform a value
        Function<String, Integer> length = s -> s.length();
        System.out.println("Length of 'Hello': " + length.apply("Hello"));

        // Consumer: perform an action
        Consumer<String> printer = msg -> System.out.println("Message: " + msg);
        printer.accept("Lambda Rocks!");

        // Supplier: provide a value
        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println("Random number: " + randomSupplier.get());

        // BiFunction: combine two values
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        System.out.println("Sum: " + sum.apply(5, 7));

        // UnaryOperator: modify a single value
        UnaryOperator<String> toUpper = str -> str.toUpperCase();
        System.out.println(toUpper.apply("java"));

        // BinaryOperator: operate on two values of same type
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        System.out.println("Product: " + multiply.apply(3, 4));

        // Streams + Lambda
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Alex");
        names.stream()
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
