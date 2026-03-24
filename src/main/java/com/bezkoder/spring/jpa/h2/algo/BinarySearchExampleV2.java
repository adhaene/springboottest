package com.bezkoder.spring.jpa.h2.algo;

import java.util.*;

public class BinarySearchExampleV2 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        int index = Collections.binarySearch(numbers, 30);
        System.out.println("Element found at index: " + index);
    }
}
