package com.company.programmers.kakao_intershiop_2020.s4;

import java.util.*;

// 새로 추가된 테스트 케이스 통과 실패 - 진입시 비용 최소값만 비교함
class Solution {
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static final int LINE_COST = 100;
    static final int CURVE_COST = 500;

    static class Spot {
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
        int n = board.length;

        int[][] costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costs[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<Spot> q = new LinkedList<>();
        if (board[1][0] != 1) {
            costs[1][0] = 100;
            q.offer(new Spot(1, 0, 0, 100));
        }
        if (board[0][1] != 1) {
            costs[0][1] = 100;
            q.offer(new Spot(0, 1, 1, 100));
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

                if (spot.dir == i && costs[nx][ny] >= spot.cost + LINE_COST) {
                    costs[nx][ny] = spot.cost + LINE_COST;
                    q.offer(new Spot(nx, ny, i, spot.cost + LINE_COST));
                }
                if (spot.dir != i && costs[nx][ny] >= spot.cost + LINE_COST + CURVE_COST){
                    costs[nx][ny] = spot.cost + LINE_COST + CURVE_COST;
                    q.offer(new Spot(nx, ny, i, spot.cost + LINE_COST + CURVE_COST));
                }
            }
        }

        return costs[n - 1][n - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(solution.solution(new int[][]{{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}}));
    }
}
