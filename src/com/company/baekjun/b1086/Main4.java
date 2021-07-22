package com.company.baekjun.b1086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        char[][] numbersChar = new char[n][];
        for (int i = 0; i <n ; i++) {
            numbersChar[i] = br.readLine().toCharArray();
        }

        int k = Integer.parseInt(br.readLine());

        int[] numbers = extractNumberModK(n, numbersChar, k);
        int[] ten = extractTenModK(k);

        long[][] dp = new long[1 << n][k];
        dp[0][0] = 1;

        long p = calcAllMod0(n, numbersChar, k, numbers, ten, dp);
        long q = calcAll(n);
        long gcd = calcGcd(p, q);

        if (p == 0) {
            System.out.println(0 + "/" + 1);
        } else {
            p /= gcd;
            q /= gcd;
            System.out.println(p + "/" + q);
        }
    }

    private static long calcGcd(long a, long b) {
        if (b == 0) {
            return a;
        }

        return calcGcd(b, a % b);
    }

    private static long calcAll(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        return result;
    }

    private static long calcAllMod0(int n, char[][] numbersChar, int k, int[] numbers, int[] ten, long[][] dp) {
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    continue;
                }

                for (int l = 0; l < k; l++) {
                    int next = l * ten[numbersChar[j].length];
                    next %= k;
                    next += numbers[j];
                    next %= k;

                    dp[i | (1 << j)][next] += dp[i][l];
                }
            }
        }

        return dp[(1 << n) - 1][0];
    }

    private static int[] extractTenModK(int k) {
        int[] ten = new int[51];
        ten[0] = 1;

        for (int i = 1; i < 51; i++) {
            ten[i] = (ten[i - 1] * 10) % k;
        }

        return ten;
    }

    private static int[] extractNumberModK(int n, char[][] numbersChar, int k) {
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (char c : numbersChar[i]) {
                temp = (temp * 10 + (c - '0')) % k;
            }
            numbers[i] = temp;
        }
        return numbers;
    }
}
