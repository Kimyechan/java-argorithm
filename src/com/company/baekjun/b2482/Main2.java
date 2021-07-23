package com.company.baekjun.b2482;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int result = solution(n, k);

        System.out.println(result);
    }

    private static int solution(int n, int k) {
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = 2; j < (i + 1) / 2 + 1; j++) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % 1_000_000_003;
            }
        }

        return (dp[n - 3][k - 1] + dp[n - 1][k]) % 1_000_000_003;
    }
}
