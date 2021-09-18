package com.company.baekjun.b1405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static double[] percents = new double[4];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    static double totalPercent = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        percents[0] = Double.parseDouble(input[1]) / 100;
        percents[2] = Double.parseDouble(input[3]) / 100;
        percents[1] = Double.parseDouble(input[2]) / 100;
        percents[3] = Double.parseDouble(input[4]) / 100;

        visited = new boolean[n * 2 + 1][n * 2 + 1];
        visited[n][n] = true;

        dfs(n, n, 1, 0);

        System.out.println(totalPercent);
    }

    private static void dfs(int x, int y, double p, int count) {
        if (count == n) {
            totalPercent += p;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!visited[nx][ny]) {
                double nextP = p * percents[i];
                visited[nx][ny] = true;
                dfs(nx, ny, nextP, count + 1);
                visited[nx][ny] = false;
            }
        }
    }
}