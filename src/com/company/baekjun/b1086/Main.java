package com.company.baekjun.b1086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 재귀로 하면 메모리 초과
public class Main {
    private static int n;
    private static int k;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        char[][] numTemp = new char[n][];
        for (int i = 0; i < n; i++) {
            numTemp[i] = br.readLine().toCharArray();
        }

        k = Integer.parseInt(br.readLine());

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < numTemp[i].length; j++) {
                sum = (int) ((sum + Integer.parseInt(String.valueOf(numTemp[i][j])) * Math.pow(10, j) % k) % k);
            }
            arr[i] = sum;
        }

        // k로 나눴을 때 나머지, 비트 마스크 경우의 수
        dp = new int[k][1 << n];
        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], -1);
        }

        long top = calcAnswerCount(0, 0);
        long bottom = factorial(n);

        if (top == 0) {
            System.out.println(0 + "/" + 1);
        } else if (top == bottom) {
            System.out.println(1 + "/" + 1);
        } else {
            long gcd = gcd(top, bottom);
            top = top / gcd;
            bottom = bottom / gcd;

            System.out.println(top + "/" + bottom);
        }
    }

    private static long gcd(long a, long b) {
        long n;

        while (b != 0) {
            n = a % b;
            a = b;
            b = n;
        }

        return a;
    }

    private static long factorial(long n) {
        long result = 1;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        return result;
    }

    private static int calcAnswerCount(int mod, int combi) {
        if (combi == (1 << n) - 1) {
            if (mod % k != 0) {
                return 0;
            }
            return 1;
        }

        if (dp[mod][combi] != -1) {
            return dp[mod][combi];
        }

        int temp = 0;
        for (int i = 0; i < n; i++) {
            if ((combi & (1 << i)) != 0) {
                continue;
            }

            int arrLen = String.valueOf(arr[i]).length();
            int decimalNum = (int) (Math.pow(10, arrLen) % k);
            arr[i] = arr[i] % k;
            int newMod = ((mod * decimalNum) % k + arr[i]) % k;

            int newCombi = combi | 1 << i;

            temp += calcAnswerCount(newMod, newCombi);
        }

        dp[mod][combi] = temp;

        return dp[mod][combi];
    }
}