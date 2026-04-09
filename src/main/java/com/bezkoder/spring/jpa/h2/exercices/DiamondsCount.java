package com.bezkoder.spring.jpa.h2.exercices;


import java.util.*;

class DiamondsCount {

    public static void main(String[] args) {
        int[] clean = {1,2,1,1};
        int[] dirty = {1,4,3,2,4};
        SocksLaundering unit = new SocksLaundering();
        System.out.println(unit.solution(2, clean,dirty));
    }

    public int solution(int[] X, int[] Y) {
        int N = X.length;

        // Store all points for O(1) lookup
        Set<Long> points = new HashSet<>();
        for (int i = 0; i < N; i++) {
            points.add(encode(X[i], Y[i]));
        }

        // Group points by x-coordinate
        Map<Integer, List<Integer>> byX = new HashMap<>();
        for (int i = 0; i < N; i++) {
            byX.computeIfAbsent(X[i], k -> new ArrayList<>()).add(Y[i]);
        }

        int diamonds = 0;

        // For each vertical line (same x)
        for (Map.Entry<Integer, List<Integer>> entry : byX.entrySet()) {
            int x = entry.getKey();
            List<Integer> ys = entry.getValue();
            Collections.sort(ys);

            int size = ys.size();
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    int y1 = ys.get(i);
                    int y2 = ys.get(j);
                    int d = y2 - y1;

                    if (d % 2 != 0) continue; // midpoint must be integer

                    int midY = (y1 + y2) / 2;
                    int half = d / 2;

                    // Check left and right vertices
                    if (points.contains(encode(x - half, midY)) &&
                            points.contains(encode(x + half, midY))) {
                        diamonds++;
                    }
                }
            }
        }

        return diamonds;
    }

    private long encode(int x, int y) {
        return (((long)x) << 32) ^ (y & 0xffffffffL);
    }
}
