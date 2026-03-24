package com.bezkoder.spring.jpa.h2.algo;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
Merge Sort is a divide-and-conquer algorithm that recursively splits an array into halves,
 sorts each half, and then merges them back together. It guarantees O(n log n) time complexity
 in all cases and is stable, making it ideal for sorting complex objects.

Enter number of elements: 6
Enter 6 integers:
38 27 43 3 9 82
Original array: [38, 27, 43, 3, 9, 82]
Sorted array:   [3, 9, 27, 38, 43, 82]

 */
public class MergeSortExample {

    // Merge two sorted subarrays into one sorted array
    private static void merge(int[] arr, int left, int mid, int right) {
        // Sizes of the two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temp arrays
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        // Merge the temp arrays back into arr
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // Copy remaining elements of leftArr, if any
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }

        // Copy remaining elements of rightArr, if any
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }

    // Recursive merge sort function
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter number of elements: ");
            int n = scanner.nextInt();

            if (n <= 0) {
                System.out.println("Array size must be positive.");
                return;
            }

            int[] arr = new int[n];
            System.out.println("Enter " + n + " integers:");
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            System.out.println("Original array: " + Arrays.toString(arr));
            mergeSort(arr, 0, arr.length - 1);
            System.out.println("Sorted array:   " + Arrays.toString(arr));

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter integers only.");
        } finally {
            scanner.close();
        }
    }
}

