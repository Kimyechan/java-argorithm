package com.company.programmers.kakao2018.프렌즈4블록;

import java.util.*;

public class Solution {
    static int[] dx = {0, 1, 0, 1};
    static int[] dy = {0, 0, 1, 1};

    static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return row == node.row && col == node.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
        System.out.println(solution.solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
    }

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        List<List<Character>> colList = initializeColList(n);
        fillColList(board, colList);

        int bombCount = 0;
        while (true) {
            Set<Node> clearSet = knowBombLocation(m, n, colList);
            if (clearSet.size() == 0) {
                break;
            }
            bombCount += clearSet.size();
            fillBombLocation(colList, clearSet);
        }

        answer = bombCount;

        return answer;
    }

    private void fillColList(String[] board, List<List<Character>> colList) {
        for (int i = 0; i < board.length; i++) {
            char[] row = board[i].toCharArray();
            for (int j = 0; j < row.length; j++) {
                colList.get(j).add(row[j]);
            }
        }
    }

    private List<List<Character>> initializeColList(int n) {
        List<List<Character>> colList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            colList.add(new ArrayList<>());
        }

        return colList;
    }

    private void fillBombLocation(List<List<Character>> colList, Set<Node> clearSet) {
        for (Node node : clearSet) {
            colList.get(node.col).remove(node.row);
            colList.get(node.col).add(0, 'X');
        }
    }

    private Set<Node> knowBombLocation(int m, int n, List<List<Character>> colList) {
        Set<Node> clearSet = new TreeSet<>((n1, n2) -> {
            if (n1.col == n2.col) {
                return n1.row - n2.row;
            } else {
                return n1.col - n2.col;
            }
        });

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (colList.get(j).get(i) == 'X') {
                    continue;
                }

                if (colList.get(j).get(i) != colList.get(j).get(i + 1)) {
                    continue;
                }

                if (colList.get(j).get(i) != colList.get(j + 1).get(i)) {
                    continue;
                }

                if (colList.get(j).get(i) != colList.get(j + 1).get(i + 1)) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {
                    clearSet.add(new Node(i + dx[k], j + dy[k]));
                }
            }
        }

        return clearSet;
    }
}