package com.bezkoder.spring.jpa.h2.exercices;


class FloodDepth {
    public static void main(String[] args) {
        int[] A = {1,3,2,1,2,1,5,3,3,4,2};

        FloodDepth unit = new FloodDepth();
        System.out.println(unit.solution(A));
    }
    public int solution(int[] A) {
        int n = A.length;
        int maxDepth = 0;

        int leftMax = A[0];
        int tempMax = 0;

        for (int i = 1; i < n; i++) {
            if (A[i] >= leftMax) {
                // New wall resets the basin
                leftMax = A[i];
                tempMax = 0;
            } else {
                // Water depth at this point
                tempMax = Math.max(tempMax, leftMax - A[i]);
                maxDepth = Math.max(maxDepth, tempMax);
            }
        }

        return maxDepth;
    }
}
