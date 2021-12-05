package com.company.programmers.weekly.퍼즐_조각_채우기;

import java.util.*;

public class Solution4 {
    List<List<Spot>> blanks = new ArrayList<>();
    List<List<Spot>> puzzles = new ArrayList<>();
    int[] dx = new int[]{0, 0, 1, -1};
    int[] dy = new int[]{1, -1, 0, 0};

    static class Spot implements Comparable<Spot>{
        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Spot o) {
            if (this.x == o.x) {
                return this.y - o.y;
            } else {
                return this.x - o.x;
            }
        }
    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        int len = game_board.length;

        boolean[][] visited = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!visited[i][j] && game_board[i][j] == 0) {
                    connectSpot(i, j, game_board, visited, blanks, 0);
                }
            }
        }

        visited = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!visited[i][j] && table[i][j] == 1) {
                    connectSpot(i, j, table, visited, puzzles, 1);
                }
            }
        }

        boolean[] usedPuzzle = new boolean[puzzles.size()];
        for (int i = 0; i < blanks.size(); i++) {
            List<Spot> blank = blanks.get(i);
            loop:
            for (int j = 0; j < puzzles.size(); j++) {
                List<Spot> puzzle = puzzles.get(j);
                if (usedPuzzle[j] || blank.size() != puzzle.size()) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {
                    rotatePuzzle(puzzle);
                    if (checkMatch(blank, puzzle)) {
                        usedPuzzle[j] = true;
                        answer += blank.size();
                        break loop;
                    }
                }
            }
        }

        return answer;
    }

    private boolean checkMatch(List<Spot> blank, List<Spot> puzzle) {
        int len = blank.size();
        for (int i = 0; i < len; i++) {
            Spot spotB = blank.get(i);
            Spot spotP = puzzle.get(i);

            if (spotB.x != spotP.x || spotB.y != spotP.y) {
                return false;
            }
        }

        return true;
    }

    private void rotatePuzzle(List<Spot> puzzle) {
        for (Spot spot : puzzle) {
            int tempX = spot.x;
            spot.x = spot.y;
            spot.y = -1 * tempX;
        }

        Collections.sort(puzzle);

        Spot firstSpot = puzzle.get(0);
        int baseX = firstSpot.x;
        int baseY = firstSpot.y;
        for (Spot spot : puzzle) {
            spot.x -= baseX;
            spot.y -= baseY;
        }
    }

    private void connectSpot(int i, int j, int[][] game_board, boolean[][] visited, List<List<Spot>> spotListList, int flag) {
        List<Spot> spots = new ArrayList<>();

        Queue<Spot> q = new LinkedList<>();
        q.offer(new Spot(i, j));

        int len = game_board.length;
        visited[i][j] = true;

        while (!q.isEmpty()) {
            Spot spot = q.poll();

            spots.add(spot);

            for (int k = 0; k < 4; k++) {
                int nx = spot.x + dx[k];
                int ny = spot.y + dy[k];

                if (nx < 0 || nx >= len || ny < 0 || ny >= len) {
                    continue;
                }

                if (visited[nx][ny] || game_board[nx][ny] != flag) {
                    continue;
                }

                visited[nx][ny] = true;
                q.offer(new Spot(nx, ny));
            }
        }

        Collections.sort(spots);

        Spot firstSpot = spots.get(0);
        int baseX = firstSpot.x;
        int baseY = firstSpot.y;
        for (Spot spot : spots) {
            spot.x -= baseX;
            spot.y -= baseY;
        }

        spotListList.add(spots);
    }

    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        int[][] g1 = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
        int[][] t1 = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};

        System.out.println(solution4.solution(g1, t1));
    }
}