package com.company.programmers.kakao2020.기둥과보설치;

public class Solution5 {
    static boolean[][] cols;
    static boolean[][] rows;
    static final int COL = 0, ROW = 1, REMOVE = 0, MAKE = 1;

    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        cols = new boolean[n + 3][n + 3];
        rows = new boolean[n + 3][n + 3];
        int count = 0;
        for (int[] frame : build_frame) {
            int x = frame[0] + 1;
            int y = frame[1] + 1;
            int kind = frame[2];
            int action = frame[3];
            if (kind == COL) {
                if (action == MAKE && checkCol(x, y)) {
                    cols[x][y] = true;
                    count++;
                }
                if (action == REMOVE && checkRemove(x, y, kind, n)) {
                    cols[x][y] = false;
                    count--;
                }
            }
            if (kind == ROW) {
                if (action == MAKE && checkRow(x, y)) {
                    rows[x][y] = true;
                    count++;
                }
                if (action == REMOVE && checkRemove(x, y, kind, n)) {
                    rows[x][y] = false;
                    count--;
                }
            }
        }

        answer = new int[count][3];
        int idx = 0;
        for (int i = 1; i < n + 2; i++) {
            for (int j = 1; j < n + 2; j++) {
                if (cols[i][j]) {
                    answer[idx] = new int[]{i - 1, j - 1, 0};
                    idx++;
                }
                if (rows[i][j]) {
                    answer[idx] = new int[]{i - 1, j - 1, 1};
                    idx++;
                }
            }
        }

        return answer;
    }

    public boolean checkCol(int x, int y) {
        return y == 1 || cols[x][y - 1] || rows[x - 1][y] || rows[x][y];
    }

    public boolean checkRow(int x, int y) {
        return cols[x][y - 1] || cols[x + 1][y - 1] || (rows[x - 1][y] && rows[x + 1][y]);
    }

    public boolean checkRemove(int x, int y, int kind, int n) {
        if (kind == COL) {
            cols[x][y] = false;
        } else {
            rows[x][y] = false;
        }

        boolean result = true;
        loop:
        for (int i = 1; i < n + 2; i++) {
            for (int j = 1; j < n + 2; j++) {
                if (cols[i][j] && !checkCol(i, j)) {
                    result = false;
                    break loop;
                }
                if (rows[i][j] && !checkRow(i, j)) {
                    result = false;
                    break loop;
                }
            }
        }

        if (kind == COL) {
            cols[x][y] = true;
        } else {
            rows[x][y] = true;
        }

        return result;
    }
}
