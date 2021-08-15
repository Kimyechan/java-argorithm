package com.company.baekjun.b3109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static int r, c;
    static char[][] pipes;
    static boolean[][] visited;
    static boolean isReached;
    static int[] dx = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);

        pipes = new char[r][c];
        for (int i = 0; i < r; i++) {
            pipes[i] = br.readLine().toCharArray();
        }

        visited = new boolean[r][c];
        int count = 0;
        for (int i = 0; i < r; i++) {
            dfs(i, 0);
            if (isReached) {
                count++;
                isReached = false;
            }
        }

        System.out.println(count);
    }

    private static void dfs(int row, int col) {
        visited[row][col] = true;

        if (col == c - 1) {
            isReached = true;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nRow = row + dx[i];
            int nCol = col + 1;

            if (nRow < 0 || nRow >= r) {
                continue;
            }

            if (visited[nRow][nCol] || pipes[nRow][nCol] == 'x') {
                continue;
            }

            dfs(nRow, nCol);
            if (isReached) {
                return;
            }
        }
    }
}