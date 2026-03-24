package com.bezkoder.spring.jpa.h2.generics;

// GenericsExample.java
import java.util.ArrayList;
import java.util.List;

// Generic class with a type parameter <T>
class Box<T> {
    private T value;

    // Constructor
    public Box(T value) {
        this.value = value;
    }

    // Getter
    public T getValue() {
        return value;
    }

    // Setter
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Box{" + "value=" + value + '}';
    }
}

public class GenericsExample {

    // Generic method that prints any type of array
    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // Bounded type parameter: accepts only Numbers or subclasses
    public static <T extends Number> double sumList(List<T> list) {
        double sum = 0.0;
        for (T num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        // Example 1: Using generic class
        Box<String> stringBox = new Box<>("Hello Generics");
        Box<Integer> intBox = new Box<>(123);

        System.out.println(stringBox);
        System.out.println(intBox);

        // Example 2: Using generic method
        Integer[] intArray = {1, 2, 3, 4};
        String[] strArray = {"A", "B", "C"};
        System.out.print("Integer Array: ");
        printArray(intArray);
        System.out.print("String Array: ");
        printArray(strArray);

        // Example 3: Using bounded type parameter
        List<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(20);
        intList.add(30);
        System.out.println("Sum of Integer List: " + sumList(intList));

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.5);
        doubleList.add(2.5);
        System.out.println("Sum of Double List: " + sumList(doubleList));
    }
}
