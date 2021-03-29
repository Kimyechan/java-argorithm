package com.company.dp.쉬운계단수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(9);
        } else {
            long[][] dp = new long[n + 1][10];

            dp[1][0] = 0;
            for (int i = 1; i < 10; i++) {
                dp[1][i] = 1;
            }

            for (int i = 2; i < n + 1; i++) {
                for (int j = 0; j < 10; j++) {
                    if (j == 0) {
                        dp[i][j] = dp[i - 1][j + 1];
                    } else if (j == 9) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = (dp[i - 1][j + 1] + dp[i - 1][j - 1]) % 1000000000;
                    }
                }
            }

            int result = 0;
            for (long i : dp[n]) {
                result += i;
                result %= 1000000000;
            }

            System.out.println(result % 1000000000);
        }
    }
}
