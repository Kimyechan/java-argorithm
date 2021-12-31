package com.company.baekjun.b1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(row[j]);
            }
        }

        if (n == 1) {
            System.out.println("(" + board[0][0] + ")");
        } else {
            String result = recursion(board, 0, 0, n);
            System.out.println(result);
        }
    }

    private static String recursion(int[][] board, int x, int y, int len) {
        if (len == 1) {
            return String.valueOf(board[x][y]);
        }

        int half = len / 2;
        String lt = recursion(board, x, y, half);
        String rt = recursion(board, x, y + half, half);
        String lb = recursion(board, x + half, y, half);
        String rb = recursion(board, x + half, y + half, half);

        if (lt.length() == 1 && lt.equals(rt) && lt.equals(lb) && lt.equals(rb)) {
            return lt;
        } else {
            return "(" + lt + rt + lb + rb + ")";
        }
    }
}
