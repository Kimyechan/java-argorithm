package com.company.dataStructure.sort.raidx_sort;

import java.util.Arrays;

public class Solution {
    static int getMax(int[] data) {
        int mx = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > mx) {
                mx = data[i];
            }
        }
        return mx;
    }

    static void countSort(int[] data, int exp) {
        int[] output = new int[data.length];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < data.length; i++) {
            count[(data[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = data.length - 1; i >= 0; i--) {
            output[count[(data[i] / exp) % 10] - 1] = data[i];
            count[(data[i] / exp) % 10]--;
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = output[i];
        }
    }

    static void radixsort(int[] data) {
        int m = getMax(data);

        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(data, exp);
        }
    }

    public static void main(String[] args) {
        int[] data = {4, 54, 2, 8, 63, 7, 55, 56};

        System.out.println("# 기수 정렬 전");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
        radixsort(data);

        System.out.println("# 기수 정렬 후");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
    }
}
