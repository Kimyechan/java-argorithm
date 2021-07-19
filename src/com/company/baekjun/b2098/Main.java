package com.company.baekjun.b2098;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int n;
    private static int[][] arr;
    private static int[][] dp;
    private static final int INF = 16 * 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        dp = new int[n][(1 << n) - 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        int result = tsp(0, 1);

        System.out.println(result);
    }

    private static int tsp(int node, int visit) {
        if (visit == (1 << n) - 1) {
            if (arr[node][0] == 0)
                return INF;
            return arr[node][0];
        }

        if (dp[node][visit] != INF) {
            return dp[node][visit];
        }

        for (int i = 0; i < n; i++) {
            int next = visit | (1 << i);
            if (arr[node][i] == 0 || (visit & (1 << i)) != 0) {
                continue;
            }

            dp[node][visit] = Math.min(dp[node][visit], tsp(i, next) + arr[node][i]);
        }

        return dp[node][visit];
    }
}
