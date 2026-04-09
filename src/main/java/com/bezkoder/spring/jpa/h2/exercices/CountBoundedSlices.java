package com.bezkoder.spring.jpa.h2.exercices;

class CountBoundedSlices {

    public static void main(String[] args) {
        int[] A = {3,5,7,6,3};
        System.out.println(solution(2,A));
    }
    public static int solution(int K, int[] A) {
        final int LIMIT = 1_000_000_000;
        int n = A.length;

        java.util.Deque<Integer> minDeque = new java.util.ArrayDeque<>();
        java.util.Deque<Integer> maxDeque = new java.util.ArrayDeque<>();

        long count = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {

            // Maintain min deque (increasing)
            while (!minDeque.isEmpty() && minDeque.peekLast() > A[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(A[right]);

            // Maintain max deque (decreasing)
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < A[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(A[right]);

            // Shrink window if invalid
            while (!minDeque.isEmpty() && !maxDeque.isEmpty()
                    && maxDeque.peekFirst() - minDeque.peekFirst() > K) {

                // Move left pointer
                if (minDeque.peekFirst() == A[left]) {
                    minDeque.pollFirst();
                }
                if (maxDeque.peekFirst() == A[left]) {
                    maxDeque.pollFirst();
                }
                left++;
            }

            // All slices ending at 'right' and starting from [left..right]
            count += (right - left + 1);

            if (count > LIMIT) {
                return LIMIT;
            }
        }

        return (int) count;
    }
}

