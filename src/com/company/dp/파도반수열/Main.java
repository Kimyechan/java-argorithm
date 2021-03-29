package com.company.dp.파도반수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            if (n == 1) {
                System.out.println(1);
            } else if (n == 2) {
                System.out.println(1);
            } else if (n == 3) {
                System.out.println(1);
            } else if (n == 4) {
                System.out.println(2);
            } else if (n == 5) {
                System.out.println(2);
            } else {
                long[] dp = new long[n + 1];
                dp[1] = 1;
                dp[2] = 1;
                dp[3] = 1;
                dp[4] = 2;
                dp[5] = 2;
                for (int j = 6; j < n + 1; j++) {
                    dp[j] = dp[j - 1] + dp[j - 5];
                }
                System.out.println(dp[n]);
            }
        }
    }
}
