package com.company.baekjun.b17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1번 -> 2번 N번 X
// N번 -> N-1, 1 X
// i -> i-1, i+1 X
public class Main {
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

        int[][] dp = new int[n][3];
        int answer = 1000 * 1000;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    dp[0][j] = cost[0][j];
                } else {
                    dp[0][j] = 1000 * 1000;
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

                answer = Math.min(answer, dp[n - 1][j]);
            }
        }

        System.out.println(answer);
    }
}
