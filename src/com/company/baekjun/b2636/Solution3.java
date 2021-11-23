package com.company.baekjun.b2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3 {
    static int row, col, count, time, remainCount;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

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

        while (count != 0) {
            makeExternalAir(0, 0);

            visited = new boolean[row][col];
            remainCount = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == 1 && !visited[i][j]) {
                        dfs(i, j);
                    }
                }
            }

            visited = new boolean[row][col];
            makeExternalAir(0, 0);

            time++;
        }

        System.out.println(time);
        System.out.println(remainCount);
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        if (checkMelt(x, y)) {
            board[x][y] = 3;
            remainCount++;
            count--;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= row || ny < 0 || ny >= col) {
                continue;
            }

            if (visited[nx][ny] || board[nx][ny] != 1) {
                continue;
            }

            dfs(nx, ny);
        }
    }

    private static boolean checkMelt(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (board[nx][ny] == 2) {
                return true;
            }
        }

        return false;
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

            if (visited[nx][ny] || board[nx][ny] == 1) {
                continue;
            }

            makeExternalAir(nx, ny);
        }
    }
}