package com.company.baekjun.b2473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static long[] liquid;
    static long[] pick = new long[3];
    static long minAbsSum = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        liquid = new long[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            liquid[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(liquid);

        for (int index = 0; index < n - 2; index++) {
            searchTwoPointer(index);
            if (minAbsSum == 0) {
                break;
            }
        }

        System.out.printf("%d %d %d", pick[0], pick[1], pick[2]);
    }

    private static void searchTwoPointer(int index) {
        int start = index + 1;
        int end = n - 1;

        while (start < end) {
            long sum = liquid[index] + liquid[start] + liquid[end];
            long absSum = Math.abs(sum);

            if (minAbsSum > absSum) {
                pick[0] = liquid[index];
                pick[1] = liquid[start];
                pick[2] = liquid[end];

                minAbsSum = absSum;
            }

            if (sum < 0) {
                start++;
            } else if (sum > 0){
                end--;
            } else {
                break;
            }
        }
    }
}