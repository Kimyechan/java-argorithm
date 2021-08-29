package com.company.programmers.kakao2019.블록게임;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public int solution(int[][] board) {
        int answer = 0;
        int rowLen = board.length;
        int colLen = board[0].length;

        int prevCount = 0;
        int blockCount = 0;
        while (true) {
            fillBlackBlock(rowLen, colLen, board);

            for (int i = 0; i < rowLen - 1; i++) {
                for (int j = 0; j < colLen - 2; j++) {
                    if (check23Blocks(i, j, board)) {
                        blockCount += 1;
                        remove23Blocks(i, j, board);
                    }
                }
            }

            for (int i = 0; i < rowLen - 2; i++) {
                for (int j = 0; j < colLen - 1; j++) {
                    if (check32Blocks(i, j, board)) {
                        blockCount += 1;
                        remove32Blocks(i, j, board);
                    }
                }
            }

            removeBlackBlock(rowLen, colLen, board);
            if (prevCount == blockCount) {
                break;
            }
            prevCount = blockCount;
        }

        answer = blockCount;

        return answer;
    }

    private void removeBlackBlock(int rowLen, int colLen, int[][] board) {
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                }
            }
        }
    }

    private void remove32Blocks(int row, int col, int[][] board) {
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 2; j++) {
                board[i][j] = 0;
            }
        }
    }

    private boolean check32Blocks(int row, int col, int[][] board) {
        Map<Integer, Integer> blockMap = new HashMap<>();
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 2; j++) {
                blockMap.put(board[i][j], blockMap.getOrDefault(board[i][j], 0) + 1);
            }
        }

        if (blockMap.containsKey(0) || !blockMap.containsKey(-1) || blockMap.keySet().size() != 2) {
            return false;
        }

        int blackBlockCount = 0;
        int colorBlockCount = 0;
        for (Integer block : blockMap.keySet()) {
            if (block == -1) {
                blackBlockCount = blockMap.get(block);
            } else {
                colorBlockCount = blockMap.get(block);
            }
        }

        return blackBlockCount == 2 && colorBlockCount == 4;
    }

    private void remove23Blocks(int row, int col, int[][] board) {
        for (int i = row; i < row + 2; i++) {
            for (int j = col; j < col + 3; j++) {
                board[i][j] = 0;
            }
        }
    }

    private void fillBlackBlock(int rowLen, int colLen, int[][] board) {
        for (int i = 0; i < colLen; i++) {
            for (int j = 0; j < rowLen; j++) {
                if (board[j][i] != 0) {
                    if (j - 1 >= 0) {
                        board[j - 1][i] = -1;
                    }
                    if (j - 2 >= 0) {
                        board[j - 2][i] = -1;
                    }
                    break;
                }
            }
        }
    }


    private boolean check23Blocks(int row, int col, int[][] board) {
        Map<Integer, Integer> blockMap = new HashMap<>();
        for (int i = row; i < row + 2; i++) {
            for (int j = col; j < col + 3; j++) {
                blockMap.put(board[i][j], blockMap.getOrDefault(board[i][j], 0) + 1);
            }
        }

        if (blockMap.containsKey(0) || !blockMap.containsKey(-1) || blockMap.keySet().size() != 2) {
            return false;
        }

        int blackBlockCount = 0;
        int colorBlockCount = 0;
        for (Integer block : blockMap.keySet()) {
            if (block == -1) {
                blackBlockCount = blockMap.get(block);
            } else {
                colorBlockCount = blockMap.get(block);
            }
        }

        return blackBlockCount == 2 && colorBlockCount == 4;
    }
}