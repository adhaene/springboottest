package com.bezkoder.spring.jpa.h2.string;

/*
Purpose: A mutable sequence of characters (unlike String, which is immutable).
Performance: Faster for frequent string modifications (append, insert, delete, replace).
Thread Safety: Not synchronized (use StringBuffer if thread safety is required).
Default Capacity: 16 characters (expands automatically as needed).
Key Methods:
append(...) – Add text to the end.
insert(offset, ...) – Insert text at a position.
delete(start, end) – Remove characters.
replace(start, end, str) – Replace characters.
reverse() – Reverse the sequence.
capacity() / ensureCapacity(n) – Manage buffer size.
setCharAt(index, ch) – Modify a character.
 */
public class StringBuilderExample {
    public static void main(String[] args) {
        // Create a StringBuilder with initial text
        StringBuilder sb = new StringBuilder("Hello");

        // Append text
        sb.append(" World");

        // Insert text
        sb.insert(5, ",");

        // Replace part of the text
        sb.replace(0, 5, "Hi");

        // Delete characters
        sb.delete(2, 3);

        // Reverse the string
        sb.reverse();

        // Delete characters at index
        sb.deleteCharAt(0);


        // Output final result
        System.out.println("Final String: " + sb);
        System.out.println("Final String: " + sb.reverse());
        System.out.println("Capacity: " + sb.capacity());
    }
}

