package com.company.programmers.kakao2020.자물쇠와열쇠;

import java.util.Arrays;

public class Solution4 {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;

        int m = key.length;
        int n = lock.length;
        int[][] lockExtend = extendLock(lock);

        for (int i = 0; i < 4; i++) {
            key = rotate90(key);

            for (int j = 0; j < 3 * n - m; j++) {
                for (int k = 0; k < 3 * n - m; k++) {
                    if (checkOpen(key, lockExtend, m, n, j, k)) {
                        answer = true;
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(lockExtend));

        return answer;
    }

    public boolean checkOpen(int[][] key, int[][] lockExtend, int m, int n, int row, int col) {
        int[][] lockExtendTemp = new int[3 * n][3 * n];
        for (int i = 0; i < 3 * n; i++) {
            for (int j = 0; j < 3 * n; j++) {
                lockExtendTemp[i][j] = lockExtend[i][j];
            }
        }

        for (int i = row; i < row + m; i++) {
            for (int j = col; j < col + m; j++) {
                lockExtendTemp[i][j] += key[i - row][j - col];
            }
        }

        boolean result = true;
        loop:
        for (int i = n; i < 2 * n; i++) {
            for (int j = n; j < 2 * n; j++) {
                if (lockExtendTemp[i][j] != 1) {
                    result = false;
                    break loop;
                }
            }
        }

        return result;
    }

    public int[][] extendLock(int[][] lock) {
        int n = lock.length;
        int[][] lockExtend = new int[n * 3][n * 3];
        for (int i = n; i < 2 * n; i++) {
            for (int j = n; j < 2 * n; j++) {
                lockExtend[i][j] = lock[i - n][j - n];
            }
        }

        return lockExtend;
    }

    public int[][] rotate90(int[][] key) {
        int m = key.length;
        int[][] keyTemp = new int[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                keyTemp[i][j] = key[m - j - 1][i];
            }
        }

        return keyTemp;
    }
}
