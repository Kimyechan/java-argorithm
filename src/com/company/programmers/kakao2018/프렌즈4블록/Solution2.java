package com.company.programmers.kakao2018.프렌즈4블록;

public class Solution2 {

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
        System.out.println(solution2.solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
    }

    public int solution(int m, int n, String[] board) {
        int answer = 0;

        char[][] blocks = new char[m][n];
        for (int i = 0; i < board.length; i++) {
            blocks[i] = board[i].toCharArray();
        }

        int totalBombCount = 0;
        while (true) {
            boolean[][] visited = new boolean[m][n];
            int bombCount = 0;
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (!checkBombBlock(i, j, blocks)) {
                        continue;
                    }
                    bombCount += countBombBlocks(i, j, visited);
                }
            }

            if (bombCount == 0) {
                break;
            }

            totalBombCount += bombCount;
            dropBlock(m, n, blocks, visited);
        }

        answer = totalBombCount;
        return answer;
    }

    private void dropBlock(int m, int n, char[][] blocks, boolean[][] visited) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    for (int k = i; k > 0; k--) {
                        blocks[k][j] = blocks[k - 1][j];
                    }
                    blocks[0][j] = 'X';
                }
            }
        }
    }

    private int countBombBlocks(int row, int col, boolean[][] visited) {
        int bombCount = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (!visited[row + i][col + j]) {
                    visited[row + i][col + j] = true;
                    bombCount++;
                }
            }
        }

        return bombCount;
    }

    private boolean checkBombBlock(int row, int col, char[][] blocks) {
        boolean isBomb = true;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (blocks[row][col] == 'X' || blocks[row][col] != blocks[row + i][col + j]) {
                    isBomb = false;
                }
            }
        }

        return isBomb;
    }
}