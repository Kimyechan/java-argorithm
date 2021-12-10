package com.company.programmers.dev_matching.행렬_테두리_회전하기;

public class Solution {
    int[][] board;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        initBoard(rows, columns);

        for (int j = 0; j < queries.length; j++) {
            int minNum = Integer.MAX_VALUE;
            int x1 = queries[j][0] - 1;
            int y1 = queries[j][1] - 1;
            int x2 = queries[j][2] - 1;
            int y2 = queries[j][3] - 1;

            int temp = board[x1][y1];
            for (int i = x1; i < x2; i++) {
                board[i][y1] = board[i + 1][y1];
                minNum = Math.min(minNum, board[i][y1]);
            }

            for (int i = y1; i < y2; i++) {
                board[x2][i] = board[x2][i + 1];
                minNum = Math.min(minNum, board[x2][i]);
            }

            for (int i = x2; i > x1; i--) {
                board[i][y2] = board[i - 1][y2];
                minNum = Math.min(minNum, board[i][y2]);
            }

            for (int i = y2; i > y1 ; i--) {
                board[x1][i] = board[x1][i - 1];
                minNum = Math.min(minNum, board[x1][i]);
            }

            board[x1][y1 + 1] = temp;
            minNum = Math.min(minNum, temp);

            answer[j] = minNum;
        }

        return answer;
    }

    private void initBoard(int rows, int columns) {
        board = new int[rows][columns];
        int number = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = number++;
            }
        }
    }
}
