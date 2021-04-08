package com.company.programmers.고득점Kit.dp.등굣길;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] dp = new int[n][m];

        for (int i = 0; i < m; i++) {
            dp[0][i] = 1;
        }

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int[] puddle : puddles) {
            if (puddle[0] - 1== 0) {
                for (int i = puddle[1] - 1; i < n; i++) {
                    dp[i][0] = - 1;
                }
            } else if (puddle[1] - 1 == 0) {
                for (int i = puddle[0] - 1; i < m; i++) {
                    dp[0][i] = -1;
                }
            } else{
                dp[puddle[1] - 1][puddle[0] - 1] = -1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j] == -1) {
                    continue;
                }

                int left = 0;
                if (dp[i][j - 1] != -1) {
                    left = dp[i][j - 1];
                }

                int top = 0;
                if (dp[i - 1][ j] != -1) {
                    top = dp[i - 1][j];
                }

                dp[i][j] = (left + top) % 1000000007;
            }
        }

        return dp[n - 1][m - 1] % 1000000007;
    }
}
