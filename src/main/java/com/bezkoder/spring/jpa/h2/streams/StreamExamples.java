package com.bezkoder.spring.jpa.h2.streams;

import java.util.*;
import java.util.stream.*;

public class StreamExamples {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Bob");

        // 1. Filtering: Names starting with 'B'
        List<String> startsWithB = names.stream()
                .filter(name -> name.startsWith("B"))
                .collect(Collectors.toList());
        System.out.println("Names starting with B: " + startsWithB);

        // 2. Mapping: Convert names to uppercase
        List<String> upperCaseNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperCaseNames);

        // 3. Sorting: Alphabetically
        List<String> sortedNames = names.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted names: " + sortedNames);

        Comparator<String> sizeComparator1 = (o1, o2) -> o1.length() - o2.length();
        Comparator<String> sizeComparator2 = Comparator.comparingInt(String::length);

        // 4. Removing duplicates
        List<String> distinctNames = names.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct names: " + distinctNames);

        // 5. Counting elements
        long count = names.stream().count();
        System.out.println("Total names: " + count);

        // 6. Reducing: Concatenate names
        String concatenated = names.stream()
                .reduce("", (a, b) -> a + b + " ");
        System.out.println("Concatenated: " + concatenated.trim());

        // 7. Numeric Streams: Sum of squares
        int sumOfSquares = IntStream.of(1, 2, 3, 4, 5)
                .map(n -> n * n)
                .sum();
        System.out.println("Sum of squares: " + sumOfSquares);

        // 8. Collect to a Set
        Set<String> nameSet = names.stream()
                .collect(Collectors.toSet());
        System.out.println("Names as Set: " + nameSet);

        // 9. Grouping by length
        Map<Integer, List<String>> groupedByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by length: " + groupedByLength);

        // 10. Any match example
        boolean hasAlice = names.stream()
                .anyMatch(name -> name.equalsIgnoreCase("Alice"));
        System.out.println("Contains 'Alice': " + hasAlice);
    }
}
