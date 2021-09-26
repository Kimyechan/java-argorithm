package com.company.baekjun.b17136;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static int[][] board = new int[10][10];
    static boolean[][] visited = new boolean[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5};
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

    public static void dfs(int x, int y, int count) {
        if (count >= minCount) {
            return;
        }

        if (x == 10) {
            minCount = count;
            return;
        }

        if (y == 10) {
            dfs(x + 1, 0, count);
            return;
        }

        if (board[x][y] == 1 && !visited[x][y]) {
            for (int i = 1; i <= 5; i++) {
                if (paper[i] != 0 && checkPossible(x, y, i)) {
                    paper[i] -= 1;
                    fillVisited(x, y, i, true);
                    dfs(x, y + 1, count + 1);
                    fillVisited(x, y, i, false);
                    paper[i] += 1;
                }
            }
        } else if (board[x][y] == 1 && visited[x][y]) {
            dfs(x, y + 1, count);
        } else if (board[x][y] == 0) {
            dfs(x, y + 1, count);
        }
    }

    private static boolean checkFull() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void fillVisited(int x, int y, int size, boolean v) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                visited[i][j] = v;
            }
        }
    }

    private static boolean checkPossible(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) {
            return false;
        }

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board[i][j] == 0 || visited[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}