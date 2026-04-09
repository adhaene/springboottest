package com.bezkoder.spring.jpa.h2.exercices;

import java.util.*;

class SlalomSkiing {

    public static void main(String[] args) {
        int[] A = {15,13,5,7,4,10,12,8,2,11,6,9,3};

        SlalomSkiing unit = new SlalomSkiing();
        System.out.println(unit.solution(A));
    }

    public int solution(int[] A) {
        int N = A.length;
        if (N <= 2) return N;

        int[] incLeft = lisLeft(A);
        int[] decMid  = lisLeft(reverseNegate(A));
        reverse(decMid);
        int[] incRight = lisRight(A);

        int best = 0;
        for (int i = 0; i < N; i++) {
            best = Math.max(best, incLeft[i] + decMid[i] + incRight[i] - 2);
        }
        return best;
    }

    private int[] lisLeft(int[] A) {
        int N = A.length;
        int[] dp = new int[N];
        int[] tail = new int[N];
        int len = 0;

        for (int i = 0; i < N; i++) {
            int pos = Arrays.binarySearch(tail, 0, len, A[i]);
            if (pos < 0) pos = -pos - 1;
            tail[pos] = A[i];
            if (pos == len) len++;
            dp[i] = pos + 1;
        }
        return dp;
    }

    private int[] lisRight(int[] A) {
        int N = A.length;
        int[] dp = new int[N];
        int[] tail = new int[N];
        int len = 0;

        for (int i = N - 1; i >= 0; i--) {
            int pos = Arrays.binarySearch(tail, 0, len, A[i]);
            if (pos < 0) pos = -pos - 1;
            tail[pos] = A[i];
            if (pos == len) len++;
            dp[i] = pos + 1;
        }
        return dp;
    }

    private int[] reverseNegate(int[] A) {
        int N = A.length;
        int[] B = new int[N];
        for (int i = 0; i < N; i++) B[i] = -A[N - 1 - i];
        return B;
    }

    private void reverse(int[] A) {
        for (int i = 0, j = A.length - 1; i < j; i++, j--) {
            int t = A[i]; A[i] = A[j]; A[j] = t;
        }
    }
}
