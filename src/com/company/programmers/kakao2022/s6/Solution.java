package com.company.programmers.kakao2022.s6;

public class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;

        int[][] boardApply = new int[n][m];
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];

            if (type == 1) {
                boardApply[r1][c1] = -1 * degree;

                if (r2 + 1 < n && c2 + 1 < m) {
                    boardApply[r2 + 1][c2 + 1] = -1 * degree;
                }
                if (r2 + 1 < n && c1 + 1 < m) {
                    boardApply[r2 + 1][c1] = degree;
                }
                if (r1 + 1 < n && c2 + 1 < m) {
                    boardApply[r1][c2 + 1] = degree;
                }
            }
            if (type == 2) {
                boardApply[r1][c1] = degree;

                if (r2 + 1 < n && c2 + 1 < m) {
                    boardApply[r2 + 1][c2 + 1] = degree;
                }
                if (r2 + 1 < n && c1 + 1 < m) {
                    boardApply[r2 + 1][c1] = -1 * degree;
                }
                if (r1 + 1 < n && c2 + 1 < m) {
                    boardApply[r1][c2 + 1] = -1 * degree;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                boardApply[i][j + 1] += boardApply[i][j];
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                boardApply[i + 1][j] += boardApply[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += boardApply[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0) {
                    answer += 1;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(
                new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}},
                new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}}
        ));
//        System.out.println(solution.solution(
//                new int[][]{{1,2,3},{4,5,6},{7,8,9}},
//                new int[][]{{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,10}}
//        ));
    }
}