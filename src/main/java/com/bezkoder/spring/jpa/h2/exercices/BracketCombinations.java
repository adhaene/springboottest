package com.bezkoder.spring.jpa.h2.exercices;

public class BracketCombinations {

    public static void main (String[] args) {
        // keep this function call here
        System.out.println(BracketCombinations(3));
        System.out.println(BracketCombinations(2));
    }

    public static int BracketCombinations(int num) {
        return generate(num, num);
    }

    private static int generate(int open, int close) {
        if (open == 0 && close == 0) return 1;

        int count = 0;

        if (open > 0) {
            count += generate(open - 1, close);
        }

        if (close > open) {
            count += generate(open, close - 1);
        }

        return count;
    }
}
