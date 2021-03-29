package com.company.dp.포도주시식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] grape = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            grape[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(grape[1]);
        } else if (n == 2) {
            System.out.println(grape[1] + grape[2]);
        } else {
            dp[1] = grape[1];
            dp[2] = grape[1] + grape[2];

            for (int i = 3; i < n + 1; i++) {
                dp[i] = Math.max(Math.max(dp[i -1], grape[i] + dp[i - 2]), grape[i] + grape[i - 1] + dp[i - 3]);
            }

            System.out.println(dp[n]);
        }
    }
}
