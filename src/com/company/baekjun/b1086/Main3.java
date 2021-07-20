package com.company.baekjun.b1086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] numbers = new String[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = br.readLine();
        }

        int k = Integer.parseInt(br.readLine());

        int[] modNumbers = calcModNumbers(n, numbers, k);
        int[] ten = calcTenMod(k);
        long[][] dp = calcDpModCount(n, numbers, k, modNumbers, ten);

        long top = dp[(1 << n) - 1][0];
        long bottom = calcAllCaseCount(n);
        long gcd = calcGcd(top, bottom);

        if (top == 0) {
            System.out.println(0 + "/" + 1);
        } else if (top == bottom) {
            System.out.println(1 + "/" + 1);
        } else {
            top = top / gcd;
            bottom = bottom / gcd;

            System.out.println(top + "/" + bottom);
        }
    }

    private static long calcGcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return calcGcd(b, a % b);
    }

    private static long calcAllCaseCount(int n) {
        long result = 1;
        for (long i = 1; i <= n; i++) {
            result *= i;
        }

        return result;
    }

    private static long[][] calcDpModCount(int n, String[] numbers, int k, int[] modNumbers, int[] ten) {
        long[][] dp = new long[1 << n][k];
        dp[0][0] = 1;

        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    continue;
                }

                for (int l = 0; l < k; l++) {
                    int next = l * ten[numbers[j].length()];
                    next %= k;
                    next += modNumbers[j];
                    next %= k;
                    dp[i | (1 << j)][next] += dp[i][l];
                }
            }
        }

        return dp;
    }

    private static int[] calcTenMod(int k) {
        int[] ten = new int[51];
        ten[0] = 1;
        for (int i = 1; i < 51; i++) {
            ten[i] = (ten[i - 1] * 10) % k;
        }

        return ten;
    }

    private static int[] calcModNumbers(int n, String[] numbers, int k) {
        int[] modNumbers = new int[n];
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = 0; j < numbers[i].length(); j++) {
                temp = temp * 10 + (numbers[i].charAt(j) - '0');
                temp %= k;
            }
            modNumbers[i] = temp;
        }

        return modNumbers;
    }
}
