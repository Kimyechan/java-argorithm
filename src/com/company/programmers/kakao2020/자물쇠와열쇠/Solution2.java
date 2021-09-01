package com.company.programmers.kakao2020.자물쇠와열쇠;

public class Solution2 {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        int m = key.length;
        int n = lock.length;

        loop:
        for (int i = 0; i < 4; i++) {
            key = rotate90(m, key);

            for (int j = 0; j < 3 * n - m; j++) {
                for (int k = 0; k < 3 * n - m; k++) {
                    if (checkOpen(j, k, n, m, key, lock)) {
                        answer = true;
                        break loop;
                    }
                }
            }
        }
        
        return answer;
    }

    private boolean checkOpen(int row, int col, int n, int m, int[][] key, int[][] lock) {
        int[][] lockExtend = new int[3 * n][3 * n];
        for (int j = n; j < 2 * n; j++) {
            for (int k = n; k < 2 * n; k++) {
                lockExtend[j][k] = lock[j - n][k - n];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                lockExtend[i + row][j + col] = lockExtend[i + row][j + col] + key[i][j];
            }
        }

        for (int i = n; i < 2 * n; i++) {
            for (int j = n; j < 2 * n; j++) {
                if (lockExtend[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }


    private int[][] rotate90(int m, int[][] key) {
        int[][] keyTemp = new int[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                keyTemp[i][j] = key[m - j - 1][i];
            }
        }
        
        return keyTemp;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
//        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}},
//                new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));

//        System.out.println(solution.solution(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
//                new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));
        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}},
                new int[][]{{1, 1, 1, 1}, {1, 1, 0, 1}, {1, 0, 1, 1}, {1, 0, 1, 1}}));
    }
}