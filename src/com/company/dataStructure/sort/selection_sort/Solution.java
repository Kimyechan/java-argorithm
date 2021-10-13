package com.company.dataStructure.sort.selection_sort;

public class Solution {
    public static void main(String[] args) {
        int[] array = { 10, 9, 1, 3, 4, 6, 15, 16, 13, 12 };

        selectionSort(array);

        for (int v : array) {
            System.out.printf("%d ", v);
        }
    }

    private static void selectionSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[minIdx] > array[j]) {
                    minIdx = j;
                }
            }
            swap(array, i, minIdx);
        }
    }

    private static void swap(int[] array, int i, int minIdx) {
        int temp = array[i];
        array[i] = array[minIdx];
        array[minIdx] = temp;
    }
}
