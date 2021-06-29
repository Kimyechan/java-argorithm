package com.company.baekjun.b14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[][] wheel = new int[4][8];
    private static int[] rotateDir = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String input[] = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = Integer.parseInt(input[j]);
            }
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            String[] input = br.readLine().split(" ");
            int wheelNum = Integer.parseInt(input[0]);
            int direction = Integer.parseInt(input[1]);
            rotateDir = new int[4];

            determinateRotateDir(wheelNum - 1, direction);
            rotateWheel();
        }

        int score = calcWheelsScore();

        System.out.println(score);
    }

    private static void determinateRotateDir(int wheelIndex, int direction) {
        rotateDir[wheelIndex] = direction;

        int prev = wheelIndex - 1;
        int next = wheelIndex + 1;

        if (prev >= 0 && rotateDir[prev] == 0) {
            if (wheel[wheelIndex][6] != wheel[prev][2]) {
                determinateRotateDir(prev, direction * - 1);
            }
        }

        if (next < 4 && rotateDir[next] == 0) {
            if (wheel[wheelIndex][2] != wheel[next][6]) {
                determinateRotateDir(next, direction * - 1);
            }
        }
    }

    private static void rotateWheel() {
        for (int i = 0; i < 4; i++) {
            if (rotateDir[i] == 1) {
                int[] temp = new int[8];
                for (int j = 1; j < 8; j++) {
                    temp[j] = wheel[i][j - 1];
                }
                temp[0] = wheel[i][7];
                wheel[i] = temp.clone();
            } else if (rotateDir[i] == -1) {
                int[] temp = new int[8];
                for (int j = 1; j < 8; j++) {
                    temp[j - 1] = wheel[i][j];
                }
                temp[7] = wheel[i][0];
                wheel[i] = temp.clone();
            }
        }
    }

    private static int calcWheelsScore() {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (wheel[i][0] == 1) {
                result += Math.pow(2, i);
            }
        }

        return result;
    }
}

