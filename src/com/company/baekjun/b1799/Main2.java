package com.company.baekjun.b1799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static int[][] board;
    static int n;
    static int blackCount = 0;
    static int whiteCount = 0;
    static int[] dx = {1, -1, 1, -1};
    static int[] dy = {-1, -1, 1, 1};

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

        bfsBlack(0, 0, 0);
        bfsWhite(0, 1, 0);

        System.out.println(blackCount + whiteCount);
    }

    private static void bfsWhite(int x, int y, int count) {
        if (x >= n) {
            whiteCount = Math.max(whiteCount, count);
            return;
        }

        if (y >= n) {
            int ny = (x + 1) % 2 == 1 ? 0 : 1;
            bfsWhite(x + 1, ny, count);
            return;
        }

        if (board[x][y] == 1 && checkPossible(x, y)) {
            board[x][y] = 2;
            bfsWhite(x, y + 2, count + 1);
            board[x][y] = 1;
        }
        bfsWhite(x, y + 2, count);
    }

    private static void bfsBlack(int x, int y, int count) {
        if (x >= n) {
            blackCount = Math.max(blackCount, count);
            return;
        }

        if (y >= n) {
            int ny = (x + 1) % 2 == 1 ? 1 : 0;
            bfsBlack(x + 1, ny, count);
            return;
        }

        if (board[x][y] == 1 && checkPossible(x, y)) {
            board[x][y] = 2;
            bfsBlack(x, y + 2, count + 1);
            board[x][y] = 1;
        }
        bfsBlack(x, y + 2, count);
    }

    private static boolean checkPossible(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            while (true) {
                nx += dx[i];
                ny += dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    break;
                }

                if (board[nx][ny] == 2) {
                    return false;
                }
            }
        }

        return true;
    }
}