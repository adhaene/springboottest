package com.bezkoder.spring.jpa.h2.algo;
/*
Heap Sort is a comparison-based sorting algorithm that uses a binary heap data structure.
It is similar to selection sort where we first find the maximum element and place it at the end.
We repeat the same process for the remaining elements.

Important Considerations

Time Complexity: The time complexity of Heap Sort is O(n log n) in all cases.
auxiliary space complexity is O(1) since it is an in-place sorting algorithm.
Stability: Heap Sort is not a stable sort. It might rearrange the relative order of equal elements.

Alternative Solutions

Merge Sort: It has the same time complexity but requires additional space.
Quick Sort: It is generally faster in practice but has a worst-case time complexity of O(n^2).

 */
public class HeapSort {
    public void sort(int arr[]) {
        int n = arr.length;
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    void heapify(int arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6, 7};
        HeapSort ob = new HeapSort();
        ob.sort(arr);
        System.out.println("Sorted array is");
        printArray(arr);
    }
}
