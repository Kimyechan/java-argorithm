package com.company.dataStructure.sort.insertion_sort;

public class Solution2 {
    public static void main(String[] args) {
        int[] array = { 10, 9, 1, 3, 4, 6, 15, 16, 13, 12 };

        insertionSort2(array);

        for (int v : array) {
            System.out.printf("%d ", v);
        }
    }

    private static void insertionSort2(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int target = array[i];

            int j = i - 1;
            while (j >= 0 && target < array[j]) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = target;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
