package com.company.baekjun.b1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] paper = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(line[j]);
            }
        }

        int minusCount = dividePaper(paper, 0, 0, n, -1);
        int zeroCount = dividePaper(paper, 0, 0, n, 0);
        int oneCount = dividePaper(paper, 0, 0, n, 1);

        System.out.println(minusCount);
        System.out.println(zeroCount);
        System.out.println(oneCount);
    }

    private static int dividePaper(int[][] paper, int row, int col, int size, int kind) {
        if (checkNumber(paper, row, col, size, kind)) {
            return 1;
        } else {
            if (size == 1) {
                return 0;
            }

            int sum = 0;
            int nextSize = size / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sum += dividePaper(paper, row + nextSize * i, col + nextSize * j, nextSize, kind);
                }
            }

            return sum;
        }
    }

    private static boolean checkNumber(int[][] paper, int row, int col, int size, int kind) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (paper[i][j] != kind) {
                    return false;
                }
            }
        }

        return true;
    }
}
