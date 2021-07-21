package com.company.baekjun.b2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
    private final static int MAX_VALUE = 16 * 1_000_000;
    private static int n;
    private static int[][] dp;
    private static int[][] w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        dp = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], MAX_VALUE);
        }

        w = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(input[j]);
            }
        }

        int result = tsp(0, 1);

        System.out.println(result);
    }

    private static int tsp(int city, int visit) {
        if (visit == ((1 << n) - 1)) {
            if (w[city][0] == 0) {
                return MAX_VALUE;
            }

            return w[city][0];
        }

        if (dp[city][visit] != MAX_VALUE) {
            return dp[city][visit];
        }

        for (int i = 0; i < n; i++) {
            if (w[city][i] == 0 || (visit & (1 << i)) != 0) {
                continue;
            }

            dp[city][visit] = Math.min(dp[city][visit], tsp(i, visit | (1 << i)) + w[city][i]);
        }

        return dp[city][visit];
    }
}
