package com.company.baekjun.b10830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        long b = Long.parseLong(input[1]);

        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(input[j]) % 1000;
            }
        }

        int[][] result = powMatrix(n, b);

        String s = makePrint(n, result);

        System.out.println(s);
    }

    private static String makePrint(int n, int[][] result) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        for (int i = 0; i < n - 1; i++) {
            sb.append(result[n - 1][i]).append(" ");
        }
        sb.append(result[n - 1][n - 1]);

        return sb.toString();
    }

    private static int[][] powMatrix(int n, long b) {
        if (b == 1) {
            return matrix;
        }

        int[][] matrixM;
        if (b % 2 == 1) {
            matrixM = powMatrix(n, b / 2);
            matrixM = multiplyMatrix(matrixM, matrixM, n);
            matrixM = multiplyMatrix(matrixM, matrix, n);
        } else {
            matrixM = powMatrix(n, b / 2);
            matrixM = multiplyMatrix(matrixM, matrixM, n);
        }

        return matrixM;
    }

    private static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2, int n) {
        int[][] matrixMTemp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    matrixMTemp[i][j] += (matrix1[i][k] * matrix2[k][j]) % 1000;
                }
                matrixMTemp[i][j] %= 1000;
            }
        }

        return matrixMTemp;
    }
}