package com.company.baekjun.b17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int r, c, t;
    static Node cleanTop, cleanBottom;
    static int[][] room;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

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

        String[] row = br.readLine().split(" ");
        r = Integer.parseInt(row[0]);
        c = Integer.parseInt(row[1]);
        t = Integer.parseInt(row[2]);

        room = new int[r][c];
        int totalDirtyCount = 0;
        for (int i = 0; i < r; i++) {
            row = br.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                room[i][j] = Integer.parseInt(row[j]);
                if (room[i][j] > 0) {
                    totalDirtyCount += room[i][j];
                }
            }
        }

        for (int i = 0; i < r; i++) {
            if (room[i][0] == -1) {
                cleanTop = new Node(i, 0);
                cleanBottom = new Node(i + 1, 0);
                break;
            }
        }

        int cleanAmount = 0;
        for (int i = 0; i < t; i++) {
            spread();
            cleanAmount += rotateTopWindow();
            cleanAmount += rotateBottomWindow();
        }

        System.out.println(totalDirtyCount - cleanAmount);
    }

    private static int rotateTopWindow() {
        int topCleanAmount = 0;

        for (int i = cleanTop.x; i > 0; i--) {
            room[i][0] = room[i -1][0];
        }

        for (int i = 0; i < c - 1; i++) {
            room[0][i] = room[0][i + 1];
        }

        for (int i = 0; i < cleanTop.x; i++) {
            room[i][c - 1] = room[i + 1][c - 1];
        }

        for (int i = c - 1; i > 0; i--) {
            room[cleanTop.x][i] = room[cleanTop.x][i - 1];
        }

        topCleanAmount += room[cleanTop.x][cleanTop.y];
        room[cleanTop.x][cleanTop.y] = -1;
        room[cleanTop.x][cleanTop.y + 1] = 0;

        return topCleanAmount;
    }

    private static int rotateBottomWindow() {
        int bottomCleanAmount = 0;

        for (int i = cleanBottom.x; i < r - 1; i++) {
            room[i][0] = room[i + 1][0];
        }

        for (int i = 0; i < c - 1; i++) {
            room[r - 1][i] = room[r - 1][i + 1];
        }

        for (int i = r - 1; i > cleanBottom.x; i--) {
            room[i][c - 1] = room[i - 1][c - 1];
        }

        for (int i = c - 1; i > 0; i--) {
            room[cleanBottom.x][i] = room[cleanBottom.x][i - 1];
        }

        bottomCleanAmount += room[cleanBottom.x][cleanBottom.y];
        room[cleanBottom.x][cleanBottom.y] = -1;
        room[cleanBottom.x][cleanBottom.y + 1] = 0;

        return bottomCleanAmount;
    }

    private static void spread() {
        Queue<Node> q = new LinkedList<>();
        int[][] plusAmount = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (room[i][j] != 0 && room[i][j] != -1) {
                    q.offer(new Node(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Node node = q.poll();
            int spreadAmount = room[node.x][node.y] / 5;
            int spreadCount = 0;

            if (spreadAmount == 0) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    continue;
                }

                if (room[nx][ny] == -1) {
                    continue;
                }

                spreadCount += 1;
                plusAmount[nx][ny] += spreadAmount;
            }

            room[node.x][node.y] -=  spreadCount * spreadAmount;
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                room[i][j] += plusAmount[i][j];
            }
        }
    }
}
