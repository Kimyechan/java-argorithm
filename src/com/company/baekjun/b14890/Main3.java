package com.company.baekjun.b14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static int n;
    static int l;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        l = Integer.parseInt(row[1]);

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            row = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (checkPossible(i, 0, true)) {
                count += 1;
            }

            if (checkPossible(0, i, false)) {
                count += 1;
            }
        }

        System.out.println(count);
    }

    private static boolean checkPossible(int row, int col, boolean isRow) {
        int[] road = new int[n];
        boolean[] visited = new boolean[n];

        if (isRow) {
            for (int i = 0; i < n; i++) {
                road[i] = map[row][i];
            }
        } else {
            for (int i = 0; i < n; i++) {
                road[i] = map[i][col];
            }
        }

        for (int i = 0; i < n - 1; i++) {
            if (road[i] == road[i + 1]) {
                continue;
            }

            if (Math.abs(road[i] - road[i + 1]) > 1) {
                return false;
            }

            if (road[i] == road[i + 1] + 1) {
                for (int j = i + 1; j <= i + l; j++) {
                    if (j >= n || road[i + 1] != road[j] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            }

            if (road[i] == road[i + 1] - 1) {
                for (int j = i; j > i - l; j--) {
                    if (j < 0 || road[i] != road[j] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            }
        }

        return true;
    }
}
