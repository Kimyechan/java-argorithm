package com.company.baekjun.b2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int[] values;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        values = new int[n];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(values);

        int minLeft = 0;
        int minRight = 0;

        int minSum = Integer.MAX_VALUE;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int sum = values[start] + values[end];

            if (Math.abs(minSum) > Math.abs(sum)) {
                minSum = sum;
                minLeft = start;
                minRight = end;
            }

            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

        System.out.printf("%d %d", values[minLeft], values[minRight]);
    }
}