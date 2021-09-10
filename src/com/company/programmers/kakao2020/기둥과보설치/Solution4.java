package com.company.programmers.kakao2020.기둥과보설치;

import java.util.Arrays;

public class Solution4 {
    boolean[][] rows;
    boolean[][] cols;
    static final int REMOVE = 0, MAKE = 1, COL = 0, ROW = 1;

    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        rows = new boolean[n + 3][n + 3];
        cols = new boolean[n + 3][n + 3];
        int count = 0;
        for (int[] frame : build_frame) {
            int x = frame[0] + 1;
            int y = frame[1] + 1;
            if (frame[2] == COL) {
                if (frame[3] == 1 && checkMakeColPossible(x, y)) {
                    cols[x][y] = true;
                    count++;
                }
                if (frame[3] == 0 && checkRemovePossible(x, y, frame[2], n)) {
                    cols[x][y] = false;
                    count--;
                }
            }
            if (frame[2] == ROW) {
                if (frame[3] == 1 && checkMakeRowPossible(x, y)) {
                    rows[x][y] = true;
                    count++;
                }
                if (frame[3] == 0 && checkRemovePossible(x, y, frame[2], n)) {
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

    private boolean checkRemovePossible(int x, int y, int kind, int n) {
        boolean result = true;
        if (kind == COL) {
            cols[x][y] = false;
        } else {
            rows[x][y] = false;
        }

        loop:
        for (int i = 1; i < n + 2; i++) {
            for (int j = 1; j < n + 2; j++) {
                if (cols[i][j] && !checkMakeColPossible(i, j)) {
                    result = false;
                    break loop;
                }
                if (rows[i][j] && !checkMakeRowPossible(i, j)) {
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

    private boolean checkMakeColPossible(int x, int y) {
        return y == 1 || cols[x][y - 1] || rows[x - 1][y] || rows[x][y];
    }

    private boolean checkMakeRowPossible(int x, int y) {
        return cols[x][y - 1] || cols[x + 1][y - 1] || (rows[x - 1][y] && rows[x + 1][y]);
    }

    public static void main(String[] args) {
        Solution4 solution = new Solution4();
//        System.out.println(Arrays.deepToString(solution.solution(5, new int[][]
//                {
//                        {1, 0, 0, 1},
//                        {1, 1, 1, 1},
//                        {2, 1, 0, 1},
//                        {2, 2, 1, 1},
//                        {5, 0, 0, 1},
//                        {5, 1, 0, 1},
//                        {4, 2, 1, 1},
//                        {3, 2, 1, 1}
//                }
//        )));
        System.out.println(Arrays.deepToString(solution.solution(5, new int[][]
                {
                        {0, 0, 0, 1},
                        {2, 0, 0, 1},
                        {4, 0, 0, 1},
                        {0, 1, 1, 1},
                        {1, 1, 1, 1},
                        {2, 1, 1, 1},
                        {3, 1, 1, 1},
                        {2, 0, 0, 0},
                        {1, 1, 1, 0},
                        {2, 2, 0, 1}
                }
        )));
    }
}