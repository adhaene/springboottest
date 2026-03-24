package com.bezkoder.spring.jpa.h2.algo;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
Build Max Heap – Rearranges the array so the largest element is at the root.
Extract Elements – Swap the root with the last element, reduce heap size, and heapify again.
Repeat until the array is sorted.

 */
public class HeapSortExample {

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Read array size
            System.out.print("Enter number of elements: ");
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("Array size must be positive.");
                return;
            }

            int[] arr = new int[n];

            // Read array elements
            System.out.println("Enter " + n + " integers:");
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            // Perform heap sort
            heapSort(arr);

            // Display sorted array
            System.out.println("Sorted array: " + Arrays.toString(arr));

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter integers only.");
        } finally {
            scanner.close();
        }
    }

    // Heap sort function
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Heapify reduced heap
            heapify(arr, i, 0);
        }
    }

    // Heapify a subtree rooted at index i
    private static void heapify(int[] arr, int heapSize, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child index
        int right = 2 * i + 2; // Right child index

        // If left child is larger than root
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected subtree
            heapify(arr, heapSize, largest);
        }
    }
}
