package com.bezkoder.spring.jpa.h2.algo;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


/*
Bubble Sort is a simple sorting algorithm that repeatedly steps through the list,
 compares adjacent elements, and swaps them if they are in the wrong order.
 This process is repeated until the list is sorted.

Time Complexity: Best Case: O(n) (when the array is already sorted and optimized with a flag to detect no swaps). Worst and Average Case: O(n²).

Space Complexity: O(1) as it sorts in place without requiring additional memory.

Stability: Bubble Sort is a stable sorting algorithm since it preserves the relative order of equal elements.

For better performance on larger datasets, consider using more efficient algorithms like Merge Sort, Quick Sort, or Heap Sort.

 */
public class BubbleSortExample {

    // Bubble Sort implementation
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        // Outer loop for passes
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Inner loop for comparisons
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no swaps occurred, array is already sorted
            if (!swapped) {
                break;
            }
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

            bubbleSort(arr);

            System.out.println("Sorted array:   " + Arrays.toString(arr));

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter integers only.");
        } finally {
            scanner.close();
        }
    }
}
