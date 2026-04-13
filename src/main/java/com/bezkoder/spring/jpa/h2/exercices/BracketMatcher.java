package com.bezkoder.spring.jpa.h2.exercices;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BracketMatcher {
//    public static String BracketMatcher(String str) {
//        // code goes here
//        char[] charArray = str.toCharArray();
//        Map<String, Integer> countMap = new HashMap<>();
//        for (char c : charArray) {
//            if (c == '(' || c == ')' ){
//                countMap.merge(String.valueOf(c), 1, Integer::sum);
//            }
//        }
//        List<Integer> values = countMap.values().stream().toList();
//        String valid  = values.get(0).equals(values.get(1)) ? "1" : "0"  ;
//        return valid;
//    }

    public static String BracketMatcher(String str) {
        int balance = 0;

        for (char c : str.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }

            // If balance goes negative, invalid
            if (balance < 0) {
                return "0";
            }
        }

        // If balance is 0, it's valid
        return balance == 0 ? "1" : "0";
    }

    public static void main (String[] args) {
        // keep this function call here
        System.out.print(BracketMatcher("(coder)(byte))"));
        System.out.print(BracketMatcher("(c(oder)) b(yte)"));
    }
}
