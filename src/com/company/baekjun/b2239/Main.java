package com.company.baekjun.b2239;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] board;
    static int[][] boardResult;
    static boolean isFinish = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];
        boardResult = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        dfs(0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(boardResult[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int x, int y) {
        if (isFinish) {
            return;
        }

        if (x >= 9) {
            isFinish = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boardResult[i][j] = board[i][j];
                }
            }
            return;
        }

        if (y >= 9) {
            dfs(x + 1, 0);
            return;
        }

        if (board[x][y] == 0) {
            boolean[] isUsed = new boolean[10];
            for (int i = 0; i < 9; i++) {
                isUsed[board[x][i]] = true;
                isUsed[board[i][y]] = true;
            }
            int divX = x / 3;
            int divY = y / 3;
            for (int i = divX * 3; i < divX * 3 + 3; i++) {
                for (int j = divY * 3; j < divY * 3 + 3; j++) {
                    isUsed[board[i][j]] = true;
                }
            }

            for (int i = 1; i < 10; i++) {
                if (!isUsed[i]) {
                    board[x][y] = i;
                    dfs(x, y + 1);
                    board[x][y] = 0;
                }
            }
        } else {
            dfs(x, y + 1);
        }
    }
}