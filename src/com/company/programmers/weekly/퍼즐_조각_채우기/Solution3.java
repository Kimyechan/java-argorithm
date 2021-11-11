package com.company.programmers.weekly.퍼즐_조각_채우기;

import java.util.*;

public class Solution3 {
    List<List<Point>> blocks = new ArrayList<>();
    List<List<Point>> puzzles = new ArrayList<>();
    int[] dx = new int[]{0, 1, 0 , -1};
    int[] dy = new int[]{1, 0, -1, 0};

    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        int boardSize = game_board.length;
        boolean[][] visited = new boolean[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (game_board[i][j] == 0 && !visited[i][j]) {
                    blocks.add(bfs(i, j, game_board, visited, 0));
                }
            }
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (table[i][j] == 1 && !visited[i][j]) {
                    puzzles.add(bfs(i, j, table, visited, 1));
                }
            }
        }

        boolean[] boardVisited = new boolean[blocks.size()];
        for (List<Point> puzzle : puzzles) {
            loop:
            for (int j = 0; j < blocks.size(); j++) {
                if (boardVisited[j]) {
                    continue;
                }
                if (blocks.get(j).size() != puzzle.size()) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {
                    if (checkMatch(blocks.get(j), puzzle)) {
                        answer += blocks.get(j).size();
                        boardVisited[j] = true;
                        break loop;
                    } else {
                        rotate(puzzle);
                    }
                }
            }
        }

        return answer;
    }

    private boolean checkMatch(List<Point> blocks, List<Point> puzzles) {
        for (int i = 0; i < blocks.size(); i++) {
            if (!blocks.get(i).equals(puzzles.get(i))) {
                return false;
            }
        }

        return true;
    }

    private void rotate(List<Point> points) {
        for (Point point : points) {
            int temp = point.x;
            point.x = point.y;
            point.y = -1 * temp;
        }

        Collections.sort(points);

        int nx = points.get(0).x;
        int ny = points.get(0).y;

        for (Point point : points) {
            point.x -= nx;
            point.y -= ny;
        }
    }

    private List<Point> bfs(int row, int col, int[][] board, boolean[][] visited, int state) {
        int boardSize = board.length;

        List<Point> points = new ArrayList<>();

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(row, col));

        visited[row][col] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();

            points.add(new Point(point.x - row, point.y - col));

            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) {
                    continue;
                }

                if (board[nx][ny] != state || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }

        Collections.sort(points);

        return points;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        int[][] g1 = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
        int[][] t1 = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};

        System.out.println(solution3.solution(g1, t1));
    }
}
