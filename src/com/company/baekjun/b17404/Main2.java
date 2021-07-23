package com.company.baekjun.b17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] cost = new int[n][3];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(input[j]);
            }
        }

        int result = solution(n, cost);

        System.out.println(result);
    }

    private static int solution(int n, int[][] cost) {
        int[][] dp = new int[n][3];
        int result = 1_000_000;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    dp[0][j] = cost[0][j];
                } else {
                    dp[0][j] = 1_000_000;
                }
            }

            for (int j = 1; j < n; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + cost[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + cost[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + cost[j][2];
            }

            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    continue;
                }
                result = Math.min(result, dp[n - 1][j]);
            }
        }

        return result;
    }
}
