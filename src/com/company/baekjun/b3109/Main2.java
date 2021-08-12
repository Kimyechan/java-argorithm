package com.company.baekjun.b3109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static int r, c;
    static char[][] pipe;
    static boolean[][] visited;
    static int totalCount;
    static boolean isFinish;
    static int[] dx = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);

        pipe = new char[r][c];
        for (int i = 0; i < r; i++) {
            pipe[i] = br.readLine().toCharArray();
        }

        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            visited[i][0] = true;
            dfs(i, 0);
            isFinish = false;
        }

        System.out.println(totalCount);
    }

    private static void dfs(int row, int col) {
        if (col == c - 1) {
            totalCount += 1;
            isFinish = true;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nRow = row + dx[i];
            int nCol = col + 1;

            if (nRow < 0 || nRow >= r) {
                continue;
            }

            if (pipe[nRow][nCol] != 'x' && !visited[nRow][nCol]) {
                visited[nRow][nCol] = true;
                dfs(nRow, nCol);
                if (isFinish) {
                    return;
                }
            }
        }
    }
}