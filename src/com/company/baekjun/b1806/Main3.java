package com.company.baekjun.b1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static int n, s;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        s = Integer.parseInt(input[1]);

        numbers = new int[n];
        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        int end = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        for (int start = 0; start < n; start++) {
            while (end < n && sum < s) {
                sum += numbers[end];
                end++;
            }

            if (sum >= s && minLen > (end - start)) {
                minLen = end - start;
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