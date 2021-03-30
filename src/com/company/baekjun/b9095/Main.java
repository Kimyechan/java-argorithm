package com.company.baekjun.b9095;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int k = 0; k < t; k++) {
            int n = Integer.parseInt(br.readLine());
            int[] dp = new int[n + 1];
            if (n == 1) {
                System.out.println(1);
            } else if (n == 2) {
                System.out.println(2);
            } else if (n == 3) {
                System.out.println(4);
            } else {
                dp[1] = 1;
                dp[2] = 2;
                dp[3] = 4;

                for (int i = 4; i < n + 1; i++) {
                    dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
                }

                System.out.println(dp[n]);
            }
        }
    }
}
