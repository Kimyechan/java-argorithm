package com.company.baekjun.b16434;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        long atk = Long.parseLong(input[1]);

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

        long minHp = 0;
        long hp = 0;
        for (int i = 0; i < n; i++) {
            if (step[i][0] == 1) { // 종류
                if (step[i][2] > atk) {
                    long time = step[i][2] / atk;
                    if (step[i][2] % atk == 0) {
                        time -= 1;
                    }
                    hp -= step[i][1] * time;
                    minHp = Math.min(minHp, hp);
                }
            } else {
                atk += step[i][1];
                if (hp + step[i][2] >= 0) {
                    hp = 0;
                } else {
                    hp += step[i][2];
                }
            }
        }

        System.out.println(Math.abs(minHp) + 1);
    }
}