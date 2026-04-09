package com.bezkoder.spring.jpa.h2.exercices;


import java.util.HashMap;
import java.util.Map;

class FirstUnique {

    public static void main(String[] args) {
        int[] A = {4,10,5,4,2,10};

        FirstUnique firstUnique = new FirstUnique();
        System.out.println(firstUnique.solution(A));
    }
    public int solution(int[] A) {
        Map<Integer, Integer> freq = new HashMap<>();

        // Count occurrences
        for (int x : A) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        // Find first unique
        for (int x : A) {
            if (freq.get(x) == 1) {
                return x;
            }
        }

        return -1;
    }
}
