package com.company.dataStructure.sort.bubble_sort;

public class Solution {
    public static void main(String[] args) {
        int[] array = { 10, 9, 1, 3, 4, 6, 15, 16, 13, 12 };

        bubbleSort(array);

        for (int v : array) {
            System.out.printf("%d ", v);
        }
    }

    private static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
