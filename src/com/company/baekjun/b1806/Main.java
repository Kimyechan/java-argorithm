package com.company.baekjun.b1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, s;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        s = Integer.parseInt(row[1]);

        numbers = new int[n];
        row = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(row[i]);
        }

        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int end = 0;
        for (int start = 0; start < n; start++) {
            while (sum < s && end < n) {
                sum += numbers[end];
                end++;
            }

            if (sum >= s) {
                minLen = Math.min(minLen, end - start);
            }

            sum -= numbers[start];
        }

        if (minLen == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minLen);
        }
    }
}