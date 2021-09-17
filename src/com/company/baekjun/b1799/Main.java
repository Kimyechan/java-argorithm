package com.company.baekjun.b1799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[][] board;
    static int maxWhiteCount = 0;
    static int maxBlackCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        dfsBlack(0, 0, 0, 0, 0);
        dfsWhite(0, 1, 0, 0, 0);

        System.out.println(maxWhiteCount + maxBlackCount);
    }

    private static void dfsBlack(int x, int y, int count, long maskA, long maskB) {
        if (x >= n) {
            maxBlackCount = Math.max(maxBlackCount, count);
            return;
        }

        if (y >= n) {
            int ny = (x + 1) % 2 == 1 ? 1 : 0;
            dfsBlack(x + 1, ny, count, maskA, maskB);
            return;
        }

        if (board[x][y] == 1 && checkPossible(maskA, maskB, x, y)) {
            board[x][y] = 2;
            dfsBlack(x, y + 2, count + 1, maskA | 1L << (x + y), maskB | 1L << (x - y + (n - 1)));
            board[x][y] = 1;
        }
        dfsBlack(x, y + 2, count, maskA, maskB);
    }

    private static void dfsWhite(int x, int y, int count, long maskA, long maskB) {
        if (x >= n) {
            maxWhiteCount = Math.max(maxWhiteCount, count);
            return;
        }

        if (y >= n) {
            int ny = (x + 1) % 2 == 1 ? 0 : 1;
            dfsWhite(x + 1, ny, count, maskA, maskB);
            return;
        }

        if (board[x][y] == 1 && checkPossible(maskA, maskB, x, y)) {
            board[x][y] = 2;
            dfsWhite(x, y + 2, count + 1, maskA | 1L << (x + y), maskB | 1L << (x - y + (n - 1)));
            board[x][y] = 1;
        }
        dfsWhite(x, y + 2, count, maskA, maskB);
    }

    private static boolean checkPossible(long maskA, long maskB, int x, int y) {
        return (maskA & (1L << (x + y))) == 0 && (maskB & (1L << (x - y + n - 1))) == 0;
    }
}