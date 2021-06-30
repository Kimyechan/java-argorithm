package com.company.baekjun.b14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Resolve {
    private static int[][] wheels = new int[4][8];
    private static int[] rotateDir = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = Integer.parseInt(input[j]);
            }
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            String[] input = br.readLine().split(" ");
            int wheelNum = Integer.parseInt(input[0]);
            int dir = Integer.parseInt(input[1]);
            rotateDir = new int[4];

            determineDir(wheelNum - 1, dir);
            rotateWheels();
        }

        int totalScore = calcScore();
        System.out.println(totalScore);
    }

    public static int calcScore() {
        int score = 0;

        for (int i = 0; i < 4; i++) {
            if (wheels[i][0] == 1) {
                score += Math.pow(2, i);
            }
        }

        return score;
    }

    public static void rotateWheels() {
        for (int i = 0; i < 4; i++) {
            if (rotateDir[i] == 1) {
                int[] temp = new int[8];
                for (int j = 1; j < 8; j++) {
                    temp[j] = wheels[i][j - 1];
                }
                temp[0] = wheels[i][7];
                wheels[i] = temp.clone();
            } else if (rotateDir[i] == -1) {
                int[] temp = new int[8];
                for (int j = 1; j < 8; j++) {
                    temp[j - 1] = wheels[i][j];
                }
                temp[7] = wheels[i][0];
                wheels[i] = temp.clone();
            }
        }
    }

    public static void determineDir(int wheelIndex, int dir) {
        rotateDir[wheelIndex] = dir;

        int next = wheelIndex + 1;
        int prev = wheelIndex - 1;

        if (prev >= 0 && rotateDir[prev] == 0) {
            if (wheels[prev][2] != wheels[wheelIndex][6]) {
                determineDir(prev, dir * - 1);
            }
        }

        if (next < 4 && rotateDir[next] == 0) {
            if (wheels[next][6] != wheels[wheelIndex][2]) {
                determineDir(next, dir * -1);
            }
        }
    }
}
