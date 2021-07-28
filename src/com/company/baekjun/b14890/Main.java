package com.company.baekjun.b14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int n;
    private static int l;
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        l = Integer.parseInt(input[1]);

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (canGo(i, 0, 0)) {
                count += 1;
            }

            if (canGo(0, i, 1)) {
                count += 1;
            }
        }

        System.out.println(count);
    }

    private static boolean canGo(int x, int y, int d) {
        int[] height = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            height[i] = (d == 0) ? map[x][y + i] : map[x + i][y];
        }

        for (int i = 0; i < n - 1; i++) {
            if (height[i] == height[i + 1]) {
                continue;
            }

            if (Math.abs(height[i] - height[i + 1]) > 1) {
                return false;
            }

            if (height[i + 1] == height[i] - 1) {
                for (int j = i + 1; j <= i + l; j++) {
                    if (j >= n || height[i + 1] != height[j] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            }

            else if (height[i + 1] == height[i] + 1) {
                for (int j = i ; j > i - l; j--) {
                    if (j < 0 || height[i] != height[j] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            }
        }

        return true;
    }
}