package com.company.baekjun.b10993;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main5 {
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int width = 1;
        int height = 1;
        int four = 4;
        int two = 2;
        for (int i = 0; i < n - 1; i++) {
            width += four;
            four *= 2;
        }
        for (int i = 0; i < n - 1; i++) {
            height += two;
            two *= 2;
        }
        board = new char[height][width];

        drawStar(0, 0, width, height, n);

        StringBuilder sb = new StringBuilder();
        if (n % 2 == 1) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j <= width / 2 + i; j++) {
                    if (board[i][j] == '*') {
                        sb.append('*');
                    } else {
                        sb.append(' ');
                    }
                }
                sb.append("\n");
            }
        } else {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width - i; j++) {
                    if (board[i][j] == '*') {
                        sb.append('*');
                    } else {
                        sb.append(' ');
                    }
                }
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static void drawStar(int wIdx, int hIdx, int wLen, int hLen, int n) {
        if (n <= 0) {
            return;
        }

        int mid = wIdx + wLen / 2;
        if (n % 2 == 1) {
            int innerLeft = mid;
            int innerRight = mid;
            for (int i = hIdx; i < hIdx + hLen; i++) {
                for (int j = innerLeft; j <= innerRight; j++) {
                    if (i == hIdx + hLen - 1 || j == innerLeft || j == innerRight) {
                        board[i][j] = '*';
                    }
                }
                innerLeft--;
                innerRight++;
            }
        } else {
            int innerLeft = wIdx;
            int innerRight = wIdx + wLen - 1;
            for (int i = hIdx; i < hIdx + hLen; i++) {
                for (int j = innerLeft; j <= innerRight; j++) {
                    if (i == hIdx || j == innerLeft || j == innerRight) {
                        board[i][j] = '*';
                    }
                }
                innerLeft++;
                innerRight--;
            }
        }

        int wPlus = (int) Math.pow(2, n - 1);
        if (n % 2 == 1) {
            drawStar(wIdx + wPlus, hIdx + hLen / 2, wLen - 2 * wPlus, hLen / 2, n - 1);
        } else {
            drawStar(wIdx + wPlus, hIdx + 1, wLen - 2 * wPlus, hLen / 2, n - 1);
        }
    }
}