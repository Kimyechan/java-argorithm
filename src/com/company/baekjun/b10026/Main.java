package com.company.baekjun.b10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n;
    static char[][] grid;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visitedView;
    static boolean[][] visitedNotView;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        grid = new char[n][n];

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // 색약 X
        visitedView = new boolean[n][n];
        int countView = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visitedView[i][j]) {
                    bfsView(i, j);
                    countView++;
                }
            }
        }

        // 색약
        visitedNotView = new boolean[n][n];
        int countNotView = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visitedNotView[i][j]) {
                    bfsNotView(i, j);
                    countNotView++;
                }
            }
        }

        System.out.printf("%d %d", countView, countNotView);
    }

    private static void bfsView(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visitedView[x][y] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (visitedView[nx][ny]) {
                    continue;
                }

                char currentColor = grid[node.x][node.y];
                if (currentColor != grid[nx][ny]) {
                    continue;
                }

                visitedView[nx][ny] = true;
                q.offer(new Node(nx, ny));
            }
        }
    }


    private static void bfsNotView(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visitedNotView[x][y] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (visitedNotView[nx][ny]) {
                    continue;
                }

                char currentColor = grid[node.x][node.y];
                if (currentColor == grid[nx][ny] || (currentColor == 'R' && grid[nx][ny] == 'G') || (currentColor == 'G' && grid[nx][ny] == 'R')) {
                    visitedNotView[nx][ny] = true;
                    q.offer(new Node(nx, ny));
                }
            }
        }
    }
}