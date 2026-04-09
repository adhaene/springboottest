package com.bezkoder.spring.jpa.h2.exercices;


class ArrayInversionCount {

    public static void main(String[] args) {
        int[] A = {-1,6,3,4,7,4};

        ArrayInversionCount unit = new ArrayInversionCount();
        System.out.println(unit.solution(A));
    }

    private static final int LIMIT = 1_000_000_000;

    public int solution(int[] A) {
        if (A.length < 2) return 0;
        int[] temp = new int[A.length];
        long count = mergeSort(A, temp, 0, A.length - 1);
        return count > LIMIT ? -1 : (int) count;
    }

    private long mergeSort(int[] A, int[] temp, int left, int right) {
        if (left >= right) return 0;

        int mid = (left + right) / 2;
        long count = 0;

        count += mergeSort(A, temp, left, mid);
        if (count > LIMIT) return count;

        count += mergeSort(A, temp, mid + 1, right);
        if (count > LIMIT) return count;

        count += merge(A, temp, left, mid, right);
        return count;
    }

    private long merge(int[] A, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        long count = 0;

        while (i <= mid && j <= right) {
            if (A[i] <= A[j]) {
                temp[k++] = A[i++];
            } else {
                temp[k++] = A[j++];
                count += (mid - i + 1); // all remaining left elements are inversions
            }
        }

        while (i <= mid) temp[k++] = A[i++];
        while (j <= right) temp[k++] = A[j++];

        System.arraycopy(temp, left, A, left, right - left + 1);
        return count;
    }
}
