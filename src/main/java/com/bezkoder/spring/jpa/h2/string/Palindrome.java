package com.bezkoder.spring.jpa.h2.string;

import java.io.IOException;

public class Palindrome {
    public static void main(String[] args) throws IOException {
        System.out.println(isAlphabeticPalindrome("abc123cba"));
        System.out.println(isAlphabeticPalindrome("Z"));
    }
    public static boolean isAlphabeticPalindrome(String code) {
        // Write your code here
        String onlyAlphaCode = code.replaceAll("[^A-Za-z]", "");
        StringBuilder sb = new StringBuilder(onlyAlphaCode).reverse();
        return onlyAlphaCode.contentEquals(sb);
    }
}

