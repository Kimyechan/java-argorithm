package com.company.programmers.kakao2020.기둥과보설치;

public class Solution {
    private int n;
    private boolean[][] cols, rows; // 기둥, 보
    private final int COLUMN = 0, ROW = 1, REMOVE = 0, ADD = 1;

    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        int count = 0;
        cols = new boolean[n + 3][n + 3];
        rows = new boolean[n + 3][n + 3];

        for (int[] frame : build_frame) {
            int x = frame[0] + 1;
            int y = frame[1] + 1;
            if (frame[2] == COLUMN) { // 기둥
                if (frame[3] == ADD && isExistCol(x, y)) { // 해당 위치에 기둥을 세울 수 있으면
                    cols[x][y] = true;
                    count++;
                }
                if (frame[3] == REMOVE && canRemove(x, y, COLUMN)) { // 삭제할 수 있으면
                    cols[x][y] = false;
                    count--;
                }
            } else { // 보
                if (frame[3] == ADD && isExistRow(x, y)) {
                    rows[x][y] = true;
                    count++;
                }
                if (frame[3] == REMOVE && canRemove(x, y, ROW)) {
                    rows[x][y] = false;
                    count--;
                }
            }
        }

        int[][] answer = new int[count][3];
        int index = 0;
        for (int i = 1; i <= n + 1; i++) { // 남아 있는 기둥과 보 배열에 저장
            for (int j = 1; j <= n + 1; j++) {
                if (cols[i][j]) answer[index++] = new int[]{i - 1, j - 1, COLUMN};
                if (rows[i][j]) answer[index++] = new int[]{i - 1, j - 1, ROW};
            }
        }
        return answer;
    }

    private boolean isExistCol(int x, int y) { // x, y 위치에 존재할 수 있는지
        return y == 1 || cols[x][y - 1] || rows[x][y] || rows[x - 1][y];
    }

    private boolean isExistRow(int x, int y) {
        return cols[x][y - 1] || cols[x + 1][y - 1] || (rows[x - 1][y] && rows[x + 1][y]);
    }

    private boolean canRemove(int x, int y, int type) {
        boolean result = true;

        if (type == COLUMN) cols[x][y] = false; // 임시로 삭제
        else rows[x][y] = false;

        loop:
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= n + 1; j++) {
                if (cols[i][j] && !isExistCol(i, j)) {
                    result = false;
                    break loop;
                }
                if (rows[i][j] && !isExistRow(i, j)) {
                    result = false;
                    break loop;
                }
            }
        }

        if (type == COLUMN) cols[x][y] = true; // 삭제했던 구조물 원상복구
        else rows[x][y] = true;

        return result;
    }
}