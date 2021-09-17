package com.company.baekjun.b10830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static int[][] matrixInit;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        Long b = Long.parseLong(input[1]);

        matrixInit = new int[n][n];
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(input[j]) % 1000;
                matrixInit[i][j] = Integer.parseInt(input[j]) % 1000;
            }
        }

        recursion(b);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void recursion(long b) {
        if (b == 1) {
            return;
        }

        if (b % 2 == 1) {
            recursion(b / 2);
            multiply();
            multiplyInit();
        } else {
            recursion(b / 2);
            multiply();
        }
    }

    private static void multiplyInit() {
        int n = matrix.length;
        int[][] matrixTemp = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, matrixTemp[i], 0, n);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = 0;
                for (int k = 0; k < n; k++) {
                    value += matrixTemp[i][k] * matrixInit[k][j];
                }
                matrix[i][j] = (value % 1000);
            }
        }
    }

    private static void multiply() {
        int n = matrix.length;
        int[][] matrixTemp = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, matrixTemp[i], 0, n);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = 0;
                for (int k = 0; k < n; k++) {
                    value += matrixTemp[i][k] * matrixTemp[k][j];
                }
                matrix[i][j] = (value % 1000);
            }
        }
    }
}