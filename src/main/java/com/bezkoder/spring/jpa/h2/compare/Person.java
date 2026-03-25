package com.bezkoder.spring.jpa.h2.compare;

public record Person(String name, Integer age) implements  Comparable<Person> {
    @Override
    public int compareTo(Person other) {
        return this.age().compareTo(other.age);
    }
}
