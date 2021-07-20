package com.company.baekjun.b1311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int n;
    private static int[][] cost;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(input[j]);
            }
        }

        dp = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        int result = calcCost(0, 0);

        System.out.println(result);
    }

    private static int calcCost(int person, int work) {
        if (work == (1 << n) - 1) {
            return 0;
        }

        if (dp[person][work] != Integer.MAX_VALUE) {
            return dp[person][work];
        }

        for (int i = 0; i < n; i++) {
            if ((work & (1 << i)) != 0) {
                continue;
            }

            int nextWork = work | (1 << i);
            dp[person][work] = Math.min(dp[person][work], calcCost(person + 1, nextWork) + cost[person][i]);
        }

        return dp[person][work];
    }
}
