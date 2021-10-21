package com.company.programmers.kakao_internship_2021.s2;

import java.util.*;

public class Solution {
    int[] dx = new int[]{1, 0, -1, 0};
    int[] dy = new int[]{0, 1, 0, -1};

    class Spot {
        int x;
        int y;
        int dis;

        public Spot(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            List<Spot> people = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (places[i][j].charAt(k) == 'P') {
                        people.add(new Spot(j, k, 0));
                    }
                }
            }

            if (bfs(people, places[i])) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private boolean bfs(List<Spot> people, String[] place) {
        for (Spot person : people) {
            Queue<Spot> q = new LinkedList<>();
            q.offer(new Spot(person.x, person.y, 0));

            int[][] distances = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }

            while (!q.isEmpty()) {
                Spot spot = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = spot.x + dx[i];
                    int ny = spot.y + dy[i];

                    if (person.x == nx && person.y == ny) {
                        continue;
                    }

                    if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                        continue;
                    }

                    if (place[nx].charAt(ny) == 'X') {
                        continue;
                    }

                    if (distances[nx][ny] <= spot.dis + 1) {
                        continue;
                    }

                    distances[nx][ny] = spot.dis + 1;
                    q.offer(new Spot(nx, ny, distances[nx][ny]));
                }
            }

            for (Spot spot : people) {
                if (distances[spot.x][spot.y] <= 2) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        })));
    }
}