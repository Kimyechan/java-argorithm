package com.company.baekjun.b10830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] boardInit;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        long b = Long.parseLong(input[1]);

        boardInit = new int[n][n];
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                boardInit[i][j] = Integer.parseInt(input[j]) % 1000;
                board[i][j] = Integer.parseInt(input[j]) % 1000;
            }
        }

        recursion(b);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append(board[i][n - 1]);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void recursion(long size) {
        if (size == 1) {
            return;
        }

        if (size % 2 == 1) {
            recursion(size / 2);
            multiplyMatrix();
            multiplyMatrixInit();
        } else {
            recursion(size / 2);
            multiplyMatrix();
        }
    }

    private static void multiplyMatrix() {
        int n = board.length;
        int[][] boardTemp  = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boardTemp[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = 0;
                for (int k = 0; k < n; k++) {
                    value += boardTemp[i][k] * boardTemp[k][j];
                }
                board[i][j] = value % 1000;
            }
        }
    }

    private static void multiplyMatrixInit() {
        int n = board.length;
        int[][] boardTemp  = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boardTemp[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = 0;
                for (int k = 0; k < n; k++) {
                    value += boardTemp[i][k] * boardInit[k][j];
                }
                board[i][j] = value % 1000;
            }
        }
    }
}