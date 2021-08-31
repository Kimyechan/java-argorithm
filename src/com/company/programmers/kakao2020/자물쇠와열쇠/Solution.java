package com.company.programmers.kakao2020.자물쇠와열쇠;

public class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;

        int m = key.length;
        int n = lock.length;

        if (m < n) {
            int[][] keyTemp = new int[n][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    keyTemp[i][j] = key[i][j];
                }
            }
            key = keyTemp;
            m = n;
        }

        for (int i = 0; i < 4; i++) {
            key = rotate90(key);
            for (int j = 0; j < 3 * m - n; j++) {
                for (int k = 0; k < 3 * m - n; k++) {
                    if (checkOpen(key, lock, j, k, m, n)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean checkOpen(int[][] key, int[][] lock, int rowM, int colM, int m, int n) {
        int[][] keyTemp = new int[3 * m][3 * m];
        for (int i = m; i < 2 * m; i++) {
            for (int j = m; j < 2 * m; j++) {
                keyTemp[i][j] = key[i - m][j - m];
            }
        }

        int[][] sumTemp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumTemp[i][j] = lock[i][j] + keyTemp[i + rowM][j + colM];
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sumTemp[i][j] == 1) {
                    count += 1;
                }
            }
        }

        return count == n * n;
    }

    private int[][] rotate90(int[][] key) {
        int len = key.length;
        int[][] keyTemp = new int[len][len];

        for (int i = 0; i < len; i++) {
            int[] col = new int[len];
            for (int j = len - 1; j >= 0; j--) {
                col[j] = key[len - j - 1][i];
            }
            keyTemp[i] = col;
        }

        return keyTemp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}},
//                new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));
        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}},
                new int[][]{{1, 1, 1, 1}, {1, 1, 0, 1}, {1, 0, 1, 1}, {1, 0, 1, 1}}));
    }
}