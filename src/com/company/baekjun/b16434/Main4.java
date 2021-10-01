package com.company.baekjun.b16434;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = sToInt(input[0]);
        long atk = sToInt(input[1]);

        int[][] stages = new int[n][3];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            stages[i] = new int[]{sToInt(input[0]), sToInt(input[1]), sToInt(input[2])};
        }

        long start = 1;
        long end = 123456 * 1000000000000L;
        while (start < end) {
            long mid = (start + end) / 2;

            if (checkPossible(stages, atk, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }

    private static boolean checkPossible(int[][] stages, long atk, long hp) {
        long maxHp = hp;

        for (int[] stage : stages) {
            if (stage[0] == 1) {
                long cnt = stage[2] / atk;
                if (stage[2] % atk == 0) {
                    cnt--;
                }
                hp = hp - cnt * stage[1];
                if (hp <= 0) {
                    return false;
                }
            } else {
                atk += stage[1];
                hp = Math.min(hp + stage[2], maxHp);
            }
        }

        return true;
    }

    private static int sToInt(String s) {
        return Integer.parseInt(s);
    }
}