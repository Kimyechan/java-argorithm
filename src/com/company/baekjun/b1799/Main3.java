package com.company.baekjun.b1799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static int n;
    static int[][] board;
    static int maxWhiteCount = 0;
    static int maxBlackCount = 0;
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {-1, 1, -1, 1};

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

        dfsWhite(0, 0, 0);
        dfsBlack(0, 1, 0);

        System.out.println(maxWhiteCount + maxBlackCount);
    }

    private static void dfsWhite(int x, int y, int count) {
        if (x == n) {
            maxWhiteCount = Math.max(maxWhiteCount, count);
            return;
        }

        if (y >= n) {
            int ny = (x + 1) % 2 == 0 ? 0 : 1;
            dfsWhite(x + 1, ny, count);
            return;
        }

        if (board[x][y] == 1 && checkPossible(x, y)) {
            board[x][y] = 2;
            dfsWhite(x, y + 2, count + 1);
            board[x][y] = 1;
        }
        dfsWhite(x, y + 2, count);
    }

    private static void dfsBlack(int x, int y, int count) {
        if (x == n) {
            maxBlackCount = Math.max(maxBlackCount, count);
            return;
        }

        if (y >= n) {
            int ny = (x + 1) % 2 == 0 ? 1 : 0;
            dfsBlack(x + 1, ny, count);
            return;
        }

        if (board[x][y] == 1 && checkPossible(x, y)) {
            board[x][y] = 2;
            dfsBlack(x, y + 2, count + 1);
            board[x][y] = 1;
        }
        dfsBlack(x, y + 2, count);
    }

    private static boolean checkPossible(int x, int y) {
        boolean result = true;

        loop:
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            while (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (board[nx][ny] == 2) {
                    result = false;
                    break loop;
                }
                nx += dx[i];
                ny += dy[i];
            }
        }

        return result;
    }
}