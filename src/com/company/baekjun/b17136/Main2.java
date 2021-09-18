package com.company.baekjun.b17136;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static int[][] board = new int[10][10];
    static boolean[][] visited = new boolean[10][10];
    static int[] paperCount = {0, 5, 5, 5, 5, 5};
    static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        dfs(0, 0, 0);
        if (minCount == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minCount);
        }
    }

    private static void dfs(int x, int y, int count) {
        if (x >= 10) {
            if (checkComplete()) {
                minCount = Math.min(minCount, count);
            }
            return;
        }

        if (minCount <= count) {
            return;
        }

        if (y >= 10) {
            dfs(x + 1, 0, count);
            return;
        }

        if (board[x][y] == 1 && !visited[x][y]) {
            for (int i = 5; i >= 1; i--) {
                if (paperCount[i] > 0 && checkPossible(x, y, i)) {
                    checkVisited(x, y, i, true);
                    paperCount[i] -= 1;
                    dfs(x, y + 1, count + 1);
                    paperCount[i] += 1;
                    checkVisited(x, y, i, false);
                }
            }
        } else {
            dfs(x, y + 1, count);
        }
    }

    private static boolean checkComplete() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void checkVisited(int x, int y, int size, boolean flag) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                visited[i][j] = flag;
            }
        }
    }

    private static boolean checkPossible(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) {
            return false;
        }

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board[i][j] != 1 || visited[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}