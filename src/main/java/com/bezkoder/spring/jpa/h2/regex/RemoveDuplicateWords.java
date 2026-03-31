package com.bezkoder.spring.jpa.h2.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveDuplicateWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input sentence
        System.out.println("Enter a sentence:");
        //String input = scanner.nextLine();
        String input = "I love Love to To tO code";
        scanner.close();

        // Regex to match repeated words (case-insensitive)
        String regex = "(?i)\\b(\\w+)\\b(?:\\s+\\1\\b)+";

        // Compile pattern with CASE_INSENSITIVE flag
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        // Replace duplicates with the first occurrence
        String result = matcher.replaceAll("$1");

        System.out.println("After removing duplicates:");
        System.out.println(result);
    }
}
