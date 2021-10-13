package com.company.dataStructure.sort.insertion_sort;

public class Solution {
    public static void main(String[] args) {
        int[] array = { 10, 9, 1, 3, 4, 6, 15, 16, 13, 12 };

        insertionSort(array);

        for (int v : array) {
            System.out.printf("%d ", v);
        }
    }

    private static void insertionSort(int[] array) {
        int n = array.length;

        for (int i = 1; i < n; i++) {
            int idx = i;
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > array[idx]) {
                    swap(array, j, idx);
                    idx = j;
                } else {
                    break;
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
