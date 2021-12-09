package com.company.baekjun.b1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            combination(0, 0, i, s, 0, numbers);
        }

        System.out.println(count);
    }

    private static void combination(int start, int size, int limit, int matchNum, int sum, int[] numbers) {
        if (size == limit) {
            if (sum == matchNum) {
                count++;
            }
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            combination(i + 1, size + 1, limit, matchNum, sum + numbers[i], numbers);
        }
    }
}
