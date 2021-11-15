package com.company.programmers.kakao2017.카카오프렌즈_컬러링_북;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    int[] dx = new int[]{1, 0, -1, 0};
    int[] dy = new int[]{0, 1, 0, -1};

    static class Spot {
        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    maxSizeOfOneArea = Math.max(bfs(i, j, picture, visited), maxSizeOfOneArea);
                    numberOfArea += 1;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int bfs(int i, int j, int[][] picture, boolean[][] visited) {
        int size = 1;
        int kind = picture[i][j];
        int m = picture.length;
        int n = picture[0].length;

        Queue<Spot> q = new LinkedList<>();
        q.offer(new Spot(i, j));

        visited[i][j] = true;

        while (!q.isEmpty()) {
            Spot spot = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = spot.x + dx[k];
                int ny = spot.y + dy[k];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }

                if (visited[nx][ny] || picture[nx][ny] != kind) {
                    continue;
                }

                visited[nx][ny] = true;
                q.offer(new Spot(nx, ny));
                size++;
            }
        }

        return size;
    }
}
