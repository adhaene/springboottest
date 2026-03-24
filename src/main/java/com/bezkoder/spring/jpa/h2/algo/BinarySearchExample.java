package com.bezkoder.spring.jpa.h2.algo;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchExample {

    /**
     * Performs binary search on a sorted array.
     * @param arr Sorted integer array
     * @param target Value to search for
     * @return Index of target if found, otherwise -1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Prevents integer overflow

            if (arr[mid] == target) {
                return mid; // Found
            } else if (arr[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        return -1; // Not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input array size
            System.out.print("Enter number of elements: ");
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("Array size must be positive.");
                return;
            }

            int[] arr = new int[n];
            System.out.println("Enter " + n + " integers (unsorted):");
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            // Sort array before binary search
            Arrays.sort(arr);
            System.out.println("Sorted array: " + Arrays.toString(arr));

            // Input target value
            System.out.print("Enter value to search: ");
            int target = scanner.nextInt();

            // Perform binary search
            int index = binarySearch(arr, target);

            if (index != -1) {
                System.out.println("Element found at index: " + index);
            } else {
                System.out.println("Element not found in the array.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter integers only.");
        } finally {
            scanner.close();
        }
    }
}
