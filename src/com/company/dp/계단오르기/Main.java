package com.company.dp.계단오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] stairs = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n + 1];
        if (n == 1){
            System.out.println(stairs[1]);
        } else if (n == 2) {
            System.out.println(stairs[1] + stairs[2]);
        } else {
            dp[1] = stairs[1];
            dp[2] = stairs[1] + stairs[2];
            for (int i = 3; i < n + 1; i++) {
                dp[i] = Math.max(dp[i - 3] + stairs[i -1] + stairs[i], dp[i - 2] + stairs[i]);
            }
            System.out.println(dp[n]);
        }
    }
}
