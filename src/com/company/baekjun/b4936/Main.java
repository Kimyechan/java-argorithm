package com.company.baekjun.b4936;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dx = new int[]{0, 0, 1, -1, 1, 1, -1, -1};
        int[] dy = new int[]{1, -1, 0, 0, -1, 1, -1, 1};

        while (true) {
            String[] wh = br.readLine().split(" ");
            if (wh[0].equals("0") && wh[1].equals("0")) {
                break;
            }

            int w = Integer.parseInt(wh[0]);
            int h = Integer.parseInt(wh[1]);

            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                String[] row = br.readLine().split(" ");
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(row[j]);
                }
            }

            boolean[][] isVisited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    isVisited[i][j] = false;
                }
            }

            int landCount = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1 && !isVisited[i][j]) {
                        landCount += 1;
                        Deque<Spot> deque = new ArrayDeque<>();
                        deque.offerFirst(new Spot(i, j));

                        while (!deque.isEmpty()) {
                            Spot currentSpot = deque.pollFirst();
                            isVisited[currentSpot.x][currentSpot.y] = true;

                            for (int k = 0; k < 8; k++) {
                                int nx = currentSpot.x + dx[k];
                                int ny = currentSpot.y + dy[k];

                                if (nx >= h || nx < 0 || ny >= w || ny < 0) {
                                    continue;
                                }

                                if (map[nx][ny] == 1 && !isVisited[nx][ny]) {
                                    isVisited[nx][ny] = true;

                                    deque.offerLast(new Spot(nx, ny));
                                }
                            }
                        }
                    }
                }
            }

            System.out.println(landCount);
        }
    }

    public static class Spot {
        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
