package com.company.baekjun.b2473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
    static int n;
    static long[] numbers;
    static long[] picks = new long[3];
    static long minAbsSum = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        numbers = new long[n];
        String[] input = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(numbers);

        for (int i = 0; i < n - 2; i++) {
            searchTwoPointer(i);
            if (minAbsSum == 0) {
                break;
            }
        }

        System.out.printf("%d %d %d", picks[0], picks[1], picks[2]);
    }

    private static void searchTwoPointer(int idx) {
        int start = idx + 1;
        int end = numbers.length - 1;

        while (start < end) {
            long sum = numbers[idx] + numbers[start] + numbers[end];

            if (minAbsSum > Math.abs(sum)) {
                picks[0] = numbers[idx];
                picks[1] = numbers[start];
                picks[2] = numbers[end];
                minAbsSum = Math.abs(sum);
            }

            if (sum < 0) {
                start++;
            } else if (sum > 0) {
                end--;
            } else {
                break;
            }
        }
    }
}