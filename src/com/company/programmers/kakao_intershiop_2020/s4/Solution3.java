package com.company.programmers.kakao_intershiop_2020.s4;

import java.util.LinkedList;
import java.util.Queue;

public class Solution3 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Spot {
        int x;
        int y;
        int cost;
        int dir;

        public Spot(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }

    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;

        int n = board.length;
        int[][][] costs = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    costs[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            costs[0][0][i] = 0;
        }

        Queue<Spot> q = new LinkedList<>();
        if (board[1][0] != 1) {
            q.offer(new Spot(1, 0, 100, 0));
            costs[1][0][0] = 100;
        }
        if (board[0][1] != 1) {
            q.offer(new Spot(0, 1, 100, 1));
            costs[0][1][1] = 100;
        }

        while (!q.isEmpty()) {
            Spot spot = q.poll();

            if (spot.x == n - 1 && spot.y == n - 1) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = spot.x + dx[i];
                int ny = spot.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (board[nx][ny] == 1) {
                    continue;
                }

                if (spot.dir == i && costs[nx][ny][i] >= spot.cost + 100) {
                    costs[nx][ny][i] = spot.cost + 100;
                    q.offer(new Spot(nx, ny, spot.cost + 100, i));
                } else if (spot.dir != i && costs[nx][ny][i] >= spot.cost + 100 + 500) {
                    costs[nx][ny][i] = spot.cost + 100 + 500;
                    q.offer(new Spot(nx, ny, spot.cost + 100 + 500, i));
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, costs[n - 1][n - 1][i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(solution.solution(new int[][]{{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}}));
    }
}
