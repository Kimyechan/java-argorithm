package com.company.programmers.kakao2018.프렌즈4블록;

public class Solution3 {

    public int solution(int m, int n, String[] board) {
        int answer = 0;

        int rowLen = board.length;
        int colLen = board[0].length();

        char[][] blocks = new char[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            blocks[i] = board[i].toCharArray();
        }

        int totalClearCount = 0;
        while (true) {
            int clearCount = 0;
            boolean[][] visited = new boolean[rowLen][colLen];

            for (int i = 0; i < rowLen - 1; i++) {
                for (int j = 0; j < colLen - 1; j++) {
                    if (check4Blocks(blocks, i, j)) {
                        clearCount += countClearBlock(visited, i, j);
                    }
                }
            }

            removeClearBlock(blocks, visited);
            if (clearCount == 0) {
                break;
            }

            totalClearCount += clearCount;
        }

        answer = totalClearCount;

        return answer;
    }

    private void removeClearBlock(char[][] blocks, boolean[][] visited) {
        int rowLen = blocks.length;
        int colLen = blocks[0].length;

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (!visited[i][j]) {
                    continue;
                }

                if (i == 0) {
                    blocks[i][j] = 'X';
                    continue;
                }

                for (int k = 0; k < i; k++) {
                    blocks[i - k][j] = blocks[i - k - 1][j];
                    blocks[i - k - 1][j] = 'X';
                }
            }
        }
    }

    private int countClearBlock(boolean[][] visited, int i, int j) {
        int clearCount = 0;
        for (int k = 0; k < 2; k++) {
            for (int l = 0; l < 2; l++) {
                if (!visited[i + k][j + l]) {
                    visited[i + k][j + l] = true;
                    clearCount += 1;
                }
            }
        }

        return clearCount;
    }

    private boolean check4Blocks(char[][] blocks, int i, int j) {
        boolean isClear = true;
        for (int k = 0; k < 2; k++) {
            for (int l = 0; l < 2; l++) {
                if (blocks[i][j] == 'X' || blocks[i][j] != blocks[i + k][j + l]) {
                    isClear = false;
                }
            }
        }

        return isClear;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
//        System.out.println(solution3.solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
    }
}