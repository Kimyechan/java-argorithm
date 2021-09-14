package com.company.baekjun.b10993;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = ' ';
            }
        }

        recursion(0, 0, width, height, n);

        StringBuilder sb = new StringBuilder();
        if (n % 2 == 0) {
            int k = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width - k; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
                k++;
            }
        } else {
            int k = 1;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width / 2 + k; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
                k++;
            }
        }
        System.out.println(sb.toString());
    }

    private static void recursion(int w, int h, int wLen, int hLen, int seq) {
        if (seq <= 0) {
            return;
        }

        int mid = (w + w + wLen) / 2;
        int left = w;
        int right = w + wLen;

        if (seq % 2 == 1) {
            int innerLeft = mid;
            int innerRight = mid;
            for (int i = h; i < h + hLen; i++) {
                for (int j = innerLeft; j <= innerRight; j++) {
                    if (i == h || i == h + hLen - 1 || j == innerLeft || j == innerRight) {
                        board[i][j] = '*';
                    }
                }
                innerLeft--;
                innerRight++;
            }
        } else {
            int innerLeft = left;
            int innerRight = right;
            for (int i = h; i < h + hLen; i++) {
                for (int j = innerLeft; j < innerRight; j++) {
                    if (i == h || i == h + hLen - 1 || j == innerLeft || j == innerRight - 1) {
                        board[i][j] = '*';
                    }
                }
                innerLeft++;
                innerRight--;
            }
        }

        int moveRight = w + (int) Math.pow(2, seq - 1);
        if (seq % 2 == 1) {
            recursion(moveRight, h + hLen / 2, wLen - (int) Math.pow(2, seq), hLen / 2, seq - 1);
        } else {
            recursion(moveRight, h + 1, wLen - (int) Math.pow(2, seq), hLen / 2, seq - 1);
        }
    }
}