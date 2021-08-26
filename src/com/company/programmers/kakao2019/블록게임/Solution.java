package com.company.programmers.kakao2019.블록게임;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int rowLen = board.length;
        int colLen = board[0].length;

        int prevCount = 0;
        int clearCount = 0;
        while (true) {
            fillBlackBlockTwoRow(board, rowLen, colLen);

            for (int i = 0; i < rowLen - 1; i++) {
                for (int j = 0; j < colLen - 2; j++) {
                    if (checkClearBlock23(board, i, j)) {
                        clearCount += 1;
                    }
                }
            }

            for (int i = 0; i < rowLen - 2; i++) {
                for (int j = 0; j < colLen - 1; j++) {
                    if (checkClearBlock32(board, i, j)) {
                        clearCount += 1;
                    }
                }
            }

            removeBlackBlocks(board, rowLen, colLen);

            if (prevCount == clearCount) {
                break;
            } else {
                prevCount = clearCount;
            }
        }

        answer = clearCount;

        return answer;
    }

    private void removeBlackBlocks(int[][] board, int rowLen, int colLen) {
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                }
            }
        }
    }

    private void fillBlackBlockTwoRow(int[][] board, int rowLen, int colLen) {
        for (int i = 0; i < colLen; i++) {
            for (int j = 0; j < rowLen; j++) {
                if (board[j][i] != 0) {
                    for (int k = j - 2; k < j; k++) {
                        if (k < 0) {
                            continue;
                        }
                        board[k][i] = -1;
                    }
                    break;
                }
            }
        }
//        for (int i = 0; i < colLen; i++) {
//            for (int j = 0; j < rowLen; j++) {
//                if (board[j][i] != 0) {
//                    for (int k = 0; k < j; k++) {
//                        board[k][i] = -1;
//                    }
//                    break;
//                }
//            }
//        }
    }

    private boolean checkClearBlock32(int[][] board, int i, int j) {
        Map<Integer, Integer> blockMap = new HashMap<>();
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 2; l++) {
                blockMap.put(board[i + k][j + l], blockMap.getOrDefault(board[i + k][j + l], 0) + 1);
            }
        }

        if (blockMap.keySet().size() != 2 || !blockMap.containsKey(-1) || blockMap.containsKey(0)) {
            return false;
        }

        int blackBlockCount = 0;
        int colorBlockCount = 0;
        for (Integer blockKey : blockMap.keySet()) {
            if (blockKey == -1) {
                blackBlockCount = blockMap.get(blockKey);
            } else {
                colorBlockCount = blockMap.get(blockKey);
            }
        }

        if (blackBlockCount == 2 && colorBlockCount == 4) {
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 2; l++) {
                    board[i + k][j + l] = 0;
                }
            }
            return true;
        }

        return false;
    }

    public boolean checkClearBlock23(int[][] board, int i, int j) {
        Map<Integer, Integer> blockMap = new HashMap<>();
        for (int k = 0; k < 2; k++) {
            for (int l = 0; l < 3; l++) {
                blockMap.put(board[i + k][j + l], blockMap.getOrDefault(board[i + k][j + l], 0) + 1);
            }
        }

        if (blockMap.keySet().size() != 2 || !blockMap.containsKey(-1) || blockMap.containsKey(0)) {
            return false;
        }

        int blackBlockCount = 0;
        int colorBlockCount = 0;
        for (Integer blockKey : blockMap.keySet()) {
            if (blockKey == -1) {
                blackBlockCount = blockMap.get(blockKey);
            } else {
                colorBlockCount = blockMap.get(blockKey);
            }
        }

        if (blackBlockCount == 2 && colorBlockCount == 4) {
            for (int k = 0; k < 2; k++) {
                for (int l = 0; l < 3; l++) {
                    board[i + k][j + l] = 0;
                }
            }
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]
                        {
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                                , {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                                , {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                                , {0, 0, 0, 6, 0, 0, 0, 0, 0, 0}
                                , {0, 0, 0, 6, 0, 0, 4, 0, 0, 0}
                                , {0, 0, 0, 6, 6, 4, 4, 0, 0, 0}
                                , {0, 0, 0, 0, 3, 0, 4, 0, 0, 0}
                                , {0, 0, 0, 2, 3, 0, 0, 0, 5, 5}
                                , {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}
                                , {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}
                        }
                )
        );
        System.out.println(solution.solution(
                new int[][]{
                        {0, 2, 0, 0},
                        {1, 2, 0, 4},
                        {1, 2, 2, 4},
                        {1, 1, 4, 4}
                }
        ));
    }
}