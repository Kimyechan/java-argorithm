package com.company.programmers.weekly.퍼즐_조각_채우기;

import java.util.*;

public class Solution2 {
    ArrayList<ArrayList<Pos>> blocks = new ArrayList<>();
    ArrayList<ArrayList<Pos>> puzzles = new ArrayList<>();
    int[] dx = new int[]{1, 0, -1, 0};
    int[] dy = new int[]{0, 1, 0, -1};

    static class Pos implements Comparable<Pos> {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        int blockSize = game_board.length;

        boolean[][] visited = new boolean[blockSize][blockSize];
        for (int i = 0; i < blockSize; i++) {
            for (int j = 0; j < blockSize; j++) {
                if (game_board[i][j] == 0 && !visited[i][j]) {
                    blocks.add(bfs(i, j, game_board, visited, 1));
                }
            }
        }

        for (int i = 0; i < blockSize; i++) {
            for (int j = 0; j < blockSize; j++) {
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < blockSize; i++) {
            for (int j = 0; j < blockSize; j++) {
                if (table[i][j] == 1 && !visited[i][j]) {
                    puzzles.add(bfs(i, j, table, visited, 0));
                }
            }
        }

        boolean[] blockVisited = new boolean[blocks.size()];
        for (ArrayList<Pos> puzzle : puzzles) {
            loop:
            for (int i = 0; i < blocks.size(); i++) {
                if (blockVisited[i]) {
                    continue;
                }

                ArrayList<Pos> block = blocks.get(i);
                // 90도 회전
                if (block.size() != puzzle.size()) {
                    continue;
                }

                for (int j = 0; j < 4; j++) {
                    rotate(puzzle);
                    if (checkMatch(block, puzzle)) {
                        answer += block.size();
                        blockVisited[i] = true;
                        break loop;
                    }
                }
            }
        }

        return answer;
    }

    private void rotate(ArrayList<Pos> puzzle) {
        for (Pos pos : puzzle) {
            int temp = pos.x;
            pos.x = pos.y;
            pos.y = -1 * temp;
        }

        Collections.sort(puzzle);

        int nx = puzzle.get(0).x;
        int ny = puzzle.get(0).y;

        for (Pos pos : puzzle) {
            pos.x -= nx;
            pos.y -= ny;
        }
    }

    private boolean checkMatch(ArrayList<Pos> block, ArrayList<Pos> puzzle) {
        for (int i = 0; i < block.size(); i++) {
            if (!block.get(i).equals(puzzle.get(i))) {
                return false;
            }
        }

        return true;
    }

    private ArrayList<Pos> bfs(int row, int col, int[][] board, boolean[][] visited, int state) {
        ArrayList<Pos> posList = new ArrayList<>();
        int blockSize = board.length;

        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(row, col));

        visited[row][col] = true;

        while (!q.isEmpty()) {
            Pos pos = q.poll();

            posList.add(new Pos(pos.x - row, pos.y - col));

            for (int i = 0; i < 4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];

                if (nx < 0 || nx >= blockSize || ny < 0 || ny >= blockSize) {
                    continue;
                }

                if (board[nx][ny] == state) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                q.offer(new Pos(nx, ny));
            }
        }

        Collections.sort(posList);

        return posList;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        int[][] g1 = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
        int[][] t1 = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};

        System.out.println(solution2.solution(g1, t1));
    }
}