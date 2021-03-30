package com.company.baekjun.b2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] paper;
    private static int white;
    private static int blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        paper = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        calcPaperCount(0, 0, n);
        System.out.println(white);
        System.out.println(blue);
    }

    private static void calcPaperCount(int row, int col, int length) {
        int w = 0;
        int b = 0;
        for (int i = row; i < row + length; i++) {
            for (int j = col; j < col + length; j++) {
                if (paper[i][j] == 0) {
                    w += 1;
                } else {
                    b += 1;
                }
            }
        }

        if (w == Math.pow(length, 2)) {
            white += 1;
            return;
        }

        if (b == Math.pow(length, 2)) {
            blue += 1;
            return;
        }

        calcPaperCount(row, col, length / 2);
        calcPaperCount(row + length / 2, col, length / 2);
        calcPaperCount(row, col + length / 2, length / 2);
        calcPaperCount(row + length / 2, col + length / 2, length / 2);
    }
}
