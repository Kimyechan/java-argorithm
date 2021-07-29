package com.company.baekjun.b2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main3 {
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

        int time = 0;
        int cnt = 1;

        while (cnt < 2) {
            meltIce();
            time += 1;

            cnt = countSeparateIce();

            if (cnt <= 0) {
                time = 0;
                break;
            }
        }

        System.out.println(time);
    }

    private static int countSeparateIce() {
        int cnt = 0;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (sea[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j, visited);
                    cnt += 1;
                }
            }
        }

        return cnt;
    }

    private static void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }

            if (sea[nx][ny] != 0 && !visited[nx][ny]) {
                dfs(nx, ny, visited);
            }
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
            Ice nextIce = q.poll();

            int meltCount = 0;
            for (int i = 0; i < 4; i++) {
                int nx = nextIce.x + dx[i];
                int ny = nextIce.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (sea[nx][ny] == 0 && !visited[nx][ny]) {
                    meltCount += 1;
                }
            }

            int height = sea[nextIce.x][nextIce.y] - meltCount;
            if (height <= 0) {
                sea[nextIce.x][nextIce.y] = 0;
            } else {
                sea[nextIce.x][nextIce.y] = height;
            }
        }
    }
}
