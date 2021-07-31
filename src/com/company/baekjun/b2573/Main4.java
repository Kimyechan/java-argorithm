package com.company.baekjun.b2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main4 {
    static int n;
    static int m;
    static int[][] sea;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Ice {
        int x;
        int y;

        public Ice(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        m = Integer.parseInt(row[1]);

        sea = new int[n][m];
        for (int i = 0; i < n; i++) {
            row = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                sea[i][j] = Integer.parseInt(row[j]);
            }
        }

        int year = 0;
        int pieceCount = 0;
        while ((pieceCount = countIcePiece()) < 2) {
            if (pieceCount <= 0) {
                year = 0;
                break;
            }

            meltIce();
            year += 1;
        }

        System.out.println(year);
    }

    private static int countIcePiece() {
        boolean[][] visited = new boolean[n][m];
        int pieceCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (sea[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j, visited);
                    pieceCount += 1;
                }
            }
        }

        return pieceCount;
    }

    private static void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }

            if (sea[nx][ny] == 0 || visited[nx][ny]) {
                continue;
            }

            dfs(nx, ny, visited);
        }
    }

    private static void meltIce() {
        Queue<Ice> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (sea[i][j] != 0) {
                    q.offer(new Ice(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Ice ice = q.poll();
            int meltCount = 0;

            for (int i = 0; i < 4; i++) {
                int nx = ice.x + dx[i];
                int ny = ice.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (sea[nx][ny] == 0 && !visited[nx][ny]) {
                    meltCount += 1;
                }
            }

            if (meltCount < sea[ice.x][ice.y]) {
                sea[ice.x][ice.y] -= meltCount;
            } else {
                sea[ice.x][ice.y] = 0;
            }
        }
    }
}
