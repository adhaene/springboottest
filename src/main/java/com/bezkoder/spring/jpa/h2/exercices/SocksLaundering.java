package com.bezkoder.spring.jpa.h2.exercices;
import java.util.*;


class SocksLaundering {

    public static void main(String[] args) {
        int[] clean = {1,2,1,1};
        int[] dirty = {1,4,3,2,4};
        SocksLaundering unit = new SocksLaundering();
        System.out.println(unit.solution(2, clean,dirty));
    }

    public int solution(int K, int[] C, int[] D) {
        Map<Integer, Integer> clean = new HashMap<>();
        Map<Integer, Integer> dirty = new HashMap<>();

        // Count clean socks
        for (int c : C) clean.put(c, clean.getOrDefault(c, 0) + 1);

        // Count dirty socks
        for (int d : D) dirty.put(d, dirty.getOrDefault(d, 0) + 1);

        int pairs = 0;

        // 1. Count existing clean pairs
        for (int color : clean.keySet()) {
            pairs += clean.get(color) / 2;
        }

        // 2. Use laundry to complete single clean socks
        for (int color : clean.keySet()) {
            if (K == 0) break;

            if (clean.get(color) % 2 == 1 && dirty.getOrDefault(color, 0) > 0) {
                // Wash one dirty sock to complete the pair
                pairs++;
                K--;
                dirty.put(color, dirty.get(color) - 1);
            }
        }

        // 3. Use remaining laundry to wash dirty pairs
        if (K >= 2) {
            for (int color : dirty.keySet()) {
                if (K < 2) break;

                int d = dirty.get(color);
                int possiblePairs = d / 2;

                int canWash = Math.min(possiblePairs, K / 2);
                pairs += canWash;
                K -= canWash * 2;
            }
        }

        return pairs;
    }
}

