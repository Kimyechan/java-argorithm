package com.company.baekjun.b16434;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int atk = Integer.parseInt(input[1]);

        int[][] step = new int[n][3];
        int kind = 0;
        int stepAtk = 0;
        int stepHP = 0;
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            kind = Integer.parseInt(input[0]);
            stepAtk = Integer.parseInt(input[1]);
            stepHP = Integer.parseInt(input[2]);
            step[i] = new int[]{kind, stepAtk, stepHP};
        }

        long ans = 0;
        long start = 1;
        long end = 123456 * (long) Math.pow(10, 12);
        while (start <= end) {
            long maxHp = (start + end) / 2;

            if (check(n, atk, step, maxHp)) {
                end = maxHp - 1;
                ans = maxHp;
            } else {
                start = maxHp + 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean check(int n, long atk, int[][] step, long maxHp) {
        long curHP = maxHp;

        for (int i = 0; i < n; i++) {
            if (step[i][0] == 1) { // 종류
                if (step[i][2] > atk) {
                    long time = step[i][2] / atk;
                    if (step[i][2] % atk == 0) {
                        time -= 1;
                    }
                    curHP -= step[i][1] * time;
                    if (curHP <= 0) {
                        return false;
                    }
                }
            } else {
                atk += step[i][1];
                if (curHP + step[i][2] >= maxHp) {
                    curHP = maxHp;
                } else {
                    curHP += step[i][2];
                }
            }
        }

        return true;
    }
}