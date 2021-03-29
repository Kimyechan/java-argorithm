package com.company.dp.가장긴바이토닉부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] up = new int[n];
        Arrays.fill(up, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i]) {
                    up[i] = Math.max(up[i], up[j] + 1);
                }
            }
        }

        int[] down = new int[n + 1];
        Arrays.fill(down, 1);
        for (int i = n - 1; i > 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (numbers[j] < numbers[i]) {
                    down[i] = Math.max(down[i], down[j] + 1);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, up[i] + down[i] - 1);
        }

        System.out.println(result);
    }
}
