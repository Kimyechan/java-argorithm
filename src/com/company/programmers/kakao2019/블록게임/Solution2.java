package com.company.programmers.kakao2019.블록게임;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    public int solution(int[][] board) {
        int answer = 0;
        int prevClearCount = 0;
        int clearCount = 0;
        int rowLen = board.length;
        int colLen = board[0].length;
        while (true) {
            fillBlackBlock(board);

            for (int i = 0; i < rowLen - 1; i++) {
                for (int j = 0; j < colLen - 2; j++) {
                    if (check23Block(i, j, board)) {
                        clearCount += 1;
                    }
                }
            }

            for (int i = 0; i < rowLen - 2; i++) {
                for (int j = 0; j < colLen - 1; j++) {
                    if (check32Block(i, j, board)) {
                        clearCount += 1;
                    }
                }
            }

            removeBlackBlock(board);

            if (prevClearCount == clearCount) {
                break;
            }
            prevClearCount = clearCount;
        }

        answer = clearCount;

        return answer;
    }

    private void removeBlackBlock(int[][] board) {
        int rowLen = board.length;
        int colLen = board[0].length;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                }
            }
        }
    }

    private boolean check32Block(int rowStart, int colStart, int[][] board) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                int blockNum = board[rowStart + i][colStart + j];
                map.put(blockNum, map.getOrDefault(blockNum, 0) + 1);
            }
        }

        if (map.size() != 2 || !map.containsKey(-1) || map.containsKey(0)) {
            return false;
        }

        int blackBlockCount = 0;
        int colorBlockCount = 0;
        for (Integer blockNum : map.keySet()) {
            if (blockNum == -1) {
                blackBlockCount = map.get(blockNum);
            } else {
                colorBlockCount = map.get(blockNum);
            }
        }

        if (blackBlockCount == 2 && colorBlockCount == 4) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    board[rowStart + i][colStart + j] = 0;
                }
            }
            return true;
        }

        return false;
    }

    private boolean check23Block(int rowStart, int colStart, int[][] board) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                int blockNum = board[rowStart + i][colStart + j];
                map.put(blockNum, map.getOrDefault(blockNum, 0) + 1);
            }
        }

        if (map.size() != 2 || !map.containsKey(-1) || map.containsKey(0)) {
            return false;
        }

        int blackBlockCount = 0;
        int colorBlockCount = 0;
        for (Integer blockNum : map.keySet()) {
            if (blockNum == -1) {
                blackBlockCount = map.get(blockNum);
            } else {
                colorBlockCount = map.get(blockNum);
            }
        }

        if (blackBlockCount == 2 && colorBlockCount == 4) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    board[rowStart + i][colStart + j] = 0;
                }
            }
            return true;
        }

        return false;
    }

    private void fillBlackBlock(int[][] board) {
        int rowLen = board.length;
        int colLen = board[0].length;

        for (int i = 0; i < colLen; i++) {
            for (int j = 0; j < rowLen; j++) {
                if (board[j][i] != 0) {
                    for (int k = j - 1; k >= j - 2; k--) {
                        if (k >= 0) {
                            board[k][i] = -1;
                        }
                    }
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
//        System.out.println(solution.solution(new int[][]
//                        {
//                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
//                                , {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
//                                , {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
//                                , {0, 0, 0, 6, 0, 0, 0, 0, 0, 0}
//                                , {0, 0, 0, 6, 0, 0, 4, 0, 0, 0}
//                                , {0, 0, 0, 6, 6, 4, 4, 0, 0, 0}
//                                , {0, 0, 0, 0, 3, 0, 4, 0, 0, 0}
//                                , {0, 0, 0, 2, 3, 0, 0, 0, 5, 5}
//                                , {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}
//                                , {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}
//                        }
//                )
//        );
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