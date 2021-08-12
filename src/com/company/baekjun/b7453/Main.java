package com.company.baekjun.b7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 좋은 방안이지만 시간초과
public class Main {
    static int n;
    static int[] A, B, C, D;
    static int[] AB, CD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            A[i] = Integer.parseInt(row[0]);
            B[i] = Integer.parseInt(row[1]);
            C[i] = Integer.parseInt(row[2]);
            D[i] = Integer.parseInt(row[3]);
        }

        AB = new int[n * n];
        CD = new int[n * n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx] = C[i] + D[j];
                idx++;
            }
        }

        Arrays.sort(CD);
        int count = 0;
        for (int i = 0; i < n * n; i++) {
            int searchSum = -1 * AB[i];
            int upperBound = binarySearchUpper(searchSum);
            int lowerBound = binarySearchLower(searchSum);
            count += upperBound - lowerBound;
        }

        System.out.println(count);
    }

    private static int binarySearchUpper(int target) {
        int start = 0;
        int end = n * n - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (target < CD[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private static int binarySearchLower(int target) {
        int start = 0;
        int end = n * n - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (target <= CD[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}