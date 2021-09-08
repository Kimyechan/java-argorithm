package com.company.programmers.kakao2020.자물쇠와열쇠;

public class Solution3 {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;

        int m = key.length;
        int n = lock.length;

        loop:
        for (int i = 0; i < 4; i++) {
            key = rotateKey90(key, m);
            for (int j = 0; j < 3 * n - m; j++) {
                for (int k = 0; k < 3 * n - m; k++) {
                    int[][] lockExtend = extendLock(lock, n);
                    if (checkOpen(j, k, m, n, key, lockExtend)) {
                        answer = true;
                        break loop;
                    }
                }
            }
        }

        return answer;
    }

    private int[][] extendLock(int[][] lock, int n) {
        int[][] lockExtend = new int[3 * n][3 * n];
        for (int i = n; i < 2 * n; i++) {
            for (int j = n; j < 2 * n; j++) {
                lockExtend[i][j] = lock[i - n][j - n];
            }
        }

        return lockExtend;
    }

    private boolean checkOpen(int row, int col, int m, int n, int[][] key, int[][] lockExtend) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                lockExtend[i + row][j + col] += key[i][j];
            }
        }

        boolean result = true;

        loop:
        for (int i = n; i < 2 * n; i++) {
            for (int j = n; j < 2 * n; j++) {
                if (lockExtend[i][j] == 0 || lockExtend[i][j] == 2) {
                    result = false;
                    break loop;
                }
            }
        }

        return result;
    }

    private int[][] rotateKey90(int[][] key, int m) {
        int[][] keyTemp = new int[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                keyTemp[i][j] = key[m - j - 1][i];
            }
        }

        return keyTemp;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}},
                new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));
//        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}},
//                new int[][]{{1, 1, 1, 1}, {1, 1, 0, 1}, {1, 0, 1, 1}, {1, 0, 1, 1}}));
    }
}