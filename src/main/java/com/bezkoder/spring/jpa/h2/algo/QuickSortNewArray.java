package com.bezkoder.spring.jpa.h2.algo;

import java.util.Arrays;

/*
Quick Sort is a divide-and-conquer algorithm that sorts an array by selecting a pivot element,
partitioning the array around the pivot, and recursively sorting the subarrays.
Below is an implementation of Quick Sort in Java that creates a new sorted array instead of sorting in place.

Pivot Selection: The last element is chosen as the pivot.

Partitioning: The array is split into three parts: left: Elements smaller than the pivot.
 equal: Elements equal to the pivot. right: Elements greater than the pivot.

Recursive Sorting: The quickSort method is called recursively on left and right.

Concatenation: The sorted subarrays are combined into a new array.
 */
public class QuickSortNewArray {
    public static int[] quickSort(int[] arr) {
        if (arr.length <= 1) {
            return arr; // Base case: already sorted
        }
        // Choose the pivot (last element)
        int pivot = arr[arr.length - 1];
        int[] left = Arrays.stream(arr).filter(x -> x < pivot).toArray();
        int[] right = Arrays.stream(arr).filter(x -> x > pivot).toArray();
        int[] equal = Arrays.stream(arr).filter(x -> x == pivot).toArray();
        // Recursively sort left and right arrays
        left = quickSort(left);
        right = quickSort(right);
        // Combine left, equal, and right arrays into a new sorted array
        return concatenate(left, equal, right);
    }
    private static int[] concatenate(int[] left, int[] equal, int[] right) {
        int[] result = new int[left.length + equal.length + right.length];
        System.arraycopy(left, 0, result, 0, left.length);
        System.arraycopy(equal, 0, result, left.length, equal.length);
        System.arraycopy(right, 0, result, left.length + equal.length, right.length);
        return result;
    }
    public static void main(String[] args) {
        int[] data = {10, 7, 8, 9, 1, 5};
        System.out.println("Unsorted Array: " + Arrays.toString(data));
        int[] sortedArray = quickSort(data);
        System.out.println("Sorted Array: " + Arrays.toString(sortedArray));
    }
}
