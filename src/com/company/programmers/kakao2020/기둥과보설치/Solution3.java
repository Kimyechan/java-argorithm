package com.company.programmers.kakao2020.기둥과보설치;

public class Solution3 {
    boolean[][] cols;
    boolean[][] rows;
    static final int REMOVE = 0, ESTABLISH = 1, COL = 0, ROW = 1;

    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        cols = new boolean[n + 3][n + 3];
        rows = new boolean[n + 3][n + 3];

        int count = 0;
        for (int[] frame : build_frame) {
            int x = frame[0] + 1;
            int y = frame[1] + 1;

            if (frame[2] == COL) {
                if (frame[3] == REMOVE && checkPossibleRemove(n, x, y, frame[2])) {
                    cols[x][y] = false;
                    count--;
                }
                if (frame[3] == ESTABLISH && checkPossibleCol(x, y)) {
                    cols[x][y] = true;
                    count++;
                }
            }

            if (frame[2] == ROW) {
                if (frame[3] == REMOVE && checkPossibleRemove(n, x, y, frame[2])) {
                    rows[x][y] = false;
                    count--;
                }
                if (frame[3] == ESTABLISH && checkPossibleRow(x, y)) {
                    rows[x][y] = true;
                    count++;
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

    private boolean checkPossibleRemove(int n, int x, int y, int kind) {
        boolean result = true;

        if (kind == COL) {
            cols[x][y] = false;
        } else {
            rows[x][y] = false;
        }

        loop:
        for (int i = 1; i < n + 2; i++) {
            for (int j = 1; j < n + 2; j++) {
                if (cols[i][j] && !checkPossibleCol(i, j)) {
                    result = false;
                    break loop;
                }
                if (rows[i][j] && !checkPossibleRow(i, j)) {
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

    private boolean checkPossibleRow(int x, int y) {
        return cols[x][y - 1] || cols[x + 1][y - 1] || (rows[x - 1][y] && rows[x + 1][y]);
    }

    private boolean checkPossibleCol(int x, int y) {
        return y == 1 || cols[x][y - 1] || rows[x - 1][y] || rows[x][y];
    }
}