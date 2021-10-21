package com.company.programmers.kakao_intershiop_2020.s4;

import java.util.LinkedList;
import java.util.Queue;

public class Solution5 {
    int[] dx = new int[]{0, 1, 0, -1};
    int[] dy = new int[]{1, 0, -1, 0};
    int[][][] costs;

    class Spot {
        int x;
        int y;
        int dir;
        int cost;

        public Spot(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }

    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;

        int n = board.length;

        initCosts(n);

        Queue<Spot> q = new LinkedList<>();

        if (board[0][1] != 1) {
            q.offer(new Spot(0, 1, 0, 100));
            costs[0][1][0] = 100;
        }

        if (board[1][0] != 1) {
            q.offer(new Spot(1, 0, 1, 100));
            costs[1][0][1] = 100;
        }

        while (!q.isEmpty()) {
            Spot spot = q.poll();

            if (spot.cost >= answer) {
                continue;
            }

            if (spot.x == n - 1 && spot.y == n - 1) {
                answer = spot.cost;
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = spot.x + dx[i];
                int ny = spot.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1) {
                    continue;
                }

                if (spot.dir != i) {
                    int nextCost = spot.cost + 100 + 500;
                    if (costs[nx][ny][i] >= nextCost) {
                        costs[nx][ny][i] = nextCost;
                        q.offer(new Spot(nx, ny, i, nextCost));
                    }
                } else {
                    int nextCost = spot.cost + 100;
                    if (costs[nx][ny][i] >= nextCost) {
                        costs[nx][ny][i] = nextCost;
                        q.offer(new Spot(nx, ny, i, nextCost));
                    }
                }
            }
        }

        return answer;
    }

    private void initCosts(int n) {
        costs = new int[n][n][4];
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
    }

    public static void main(String[] args) {
        Solution5 solution = new Solution5();
//        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(solution.solution(new int[][]{{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}}));
    }
}
