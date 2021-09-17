package com.company.baekjun.b17136;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] board = new int[10][10];
    static boolean[][] visited = new boolean[10][10];
    static int[] paperCount = new int[]{0, 5, 5, 5, 5, 5};
    static int count = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        dfs(0, 0, 0);

        if (count == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(count);
        }
    }

    private static void dfs(int x, int y, int cnt) {
        if (x >= 10) {
            count = Math.min(count, cnt);
            return;
        }

        if (cnt >= count) {
            return;
        }

        if (y >= 10) {
            dfs(x + 1, 0, cnt);
            return;
        }

        if (board[x][y] == 1 && !visited[x][y]) {
            for (int i = 5; i >= 1; i--) {
                if (paperCount[i] > 0 && checkPossible(x, y, i)) {
                    paperCount[i] -= 1;
                    attach(x, y, i);
                    dfs(x, y + 1, cnt + 1);
                    detach(x, y, i);
                    paperCount[i] += 1;
                }
            }
        } else {
            dfs(x, y + 1, cnt);
        }
    }

    private static void detach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                visited[i][j] = false;
            }
        }
    }

    private static void attach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                visited[i][j] = true;
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