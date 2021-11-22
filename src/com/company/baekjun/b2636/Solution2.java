package com.company.baekjun.b2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {
    static int row, col, count, answer, lastRemain;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        board = new int[row][col];
        visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    count++;
                }
            }
        }

        makeExternalAir(0, 0);

        while (count != 0) {
            visited = new boolean[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == 1 && !visited[i][j]) {
                        dfs(i, j);
                    }
                }
            }

            lastRemain = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == 3) {
                        lastRemain++;
                    }
                }
            }

            visited = new boolean[row][col];
            makeExternalAir(0, 0);

            answer++;
        }

        System.out.println(answer);
        System.out.println(lastRemain);
    }

    private static void makeExternalAir(int x, int y) {
        visited[x][y] = true;
        board[x][y] = 2;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= row || ny < 0 || ny >= col) {
                continue;
            }

            if (board[nx][ny] == 1 || visited[nx][ny]) {
                continue;
            }

            makeExternalAir(nx, ny);
        }
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        if (checkMelt(x, y)) {
            board[x][y] = 3;
            count--;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= row || ny < 0 || ny >= col) {
                continue;
            }

            if (board[nx][ny] != 1 || visited[nx][ny]) {
                continue;
            }

            dfs(nx, ny);
        }
    }

    private static boolean checkMelt(int x, int y) {
        int outAirCount = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= row || ny < 0 || ny >= col) {
                continue;
            }

            if (board[nx][ny] == 2) {
                outAirCount++;
            }
        }

        return outAirCount >= 1;
    }
}
