package com.bezkoder.spring.jpa.h2.streams;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.*;

public class AdvancedStreamExamples {

    public static void main(String[] args) {
        // Sample data: List of people with hobbies
        List<Person> people = Arrays.asList(
                new Person("Alice", 25, Arrays.asList("Reading", "Cycling")),
                new Person("Bob", 32, Arrays.asList("Gaming", "Cooking")),
                new Person("Charlie", 28, Arrays.asList("Cycling", "Swimming")),
                new Person("David", 40, Arrays.asList("Reading", "Gaming")),
                new Person("Eve", 35, Arrays.asList("Cooking", "Swimming"))
        );

        // 1. flatMap: Get all unique hobbies from all people
        Set<String> allHobbies = people.stream()
                .flatMap(person -> person.getHobbies().stream())
                .collect(Collectors.toSet());
        System.out.println("All hobbies: " + allHobbies);

        // 2. Parallel Stream: Sum of ages (parallel processing)
        int totalAge = people.parallelStream()
                .mapToInt(Person::getAge)
                .sum();
        System.out.println("Total age (parallel): " + totalAge);

        // 3. Grouping by age range
        Map<String, List<Person>> ageGroups = people.stream()
                .collect(Collectors.groupingBy(p -> {
                    if (p.getAge() < 30) return "Young";
                    else if (p.getAge() <= 35) return "Mid-age";
                    else return "Senior";
                }));
        System.out.println("Grouped by age range: " + ageGroups);

        // 4. Partitioning: People above 30 vs. 30 or below
        Map<Boolean, List<Person>> partitioned = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() > 30));
        System.out.println("Partitioned by age > 30: " + partitioned);

        // 5. Custom Collector: Join names in uppercase, separated by " | "
        String customJoinedNames = people.stream()
                .map(Person::getName)
                .map(String::toUpperCase)
                .collect(Collectors.joining(" | "));
        System.out.println("Custom joined names: " + customJoinedNames);

        // 6. Concurrent grouping (thread-safe) using parallel stream
        ConcurrentMap<Integer, List<Person>> concurrentGrouping =
                people.parallelStream()
                        .collect(Collectors.groupingByConcurrent(Person::getAge));
        System.out.println("Concurrent grouping by age: " + concurrentGrouping);
    }

    // Person class
    static class Person {
        private final String name;
        private final int age;
        private final List<String> hobbies;

        public Person(String name, int age, List<String> hobbies) {
            this.name = name;
            this.age = age;
            this.hobbies = hobbies;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public List<String> getHobbies() { return hobbies; }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}
