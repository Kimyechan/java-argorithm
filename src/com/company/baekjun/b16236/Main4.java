package com.company.baekjun.b16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main4 {
    static int n;
    static int[][] sea;
    static int totalTime;
    static int size = 2;
    static int eatingCount;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Spot {
        int x;
        int y;
        int time;

        public Spot() {
        }

        public Spot(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        sea = new int[n][n];
        Spot firstShark = new Spot();

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                sea[i][j] = Integer.parseInt(row[j]);
                if (sea[i][j] == 9) {
                    firstShark = new Spot(i, j, 0);
                }
            }
        }

        moveShark(firstShark);

        System.out.println(totalTime);
    }

    private static void moveShark(Spot firstShark) {
        Queue<Spot> q = new LinkedList<>();
        q.offer(firstShark);
        Spot tempShark = firstShark;
        Queue<Spot> fishQ = new LinkedList<>();

        while (true) {
            boolean[][] visited = new boolean[n][n];

            while (!q.isEmpty()) {
                Spot nextShark = q.poll();
                visited[nextShark.x][nextShark.y] = true;

                for (int i = 0; i < 4; i++) {
                    int nx = nextShark.x + dx[i];
                    int ny = nextShark.y + dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }

                    if (sea[nx][ny] > size || visited[nx][ny]) {
                        continue;
                    }

                    if (sea[nx][ny] != 0 && sea[nx][ny] < size) {
                        fishQ.offer(new Spot(nx, ny, nextShark.time + 1));
                    }

                    visited[nx][ny] = true;
                    q.offer(new Spot(nx, ny, nextShark.time + 1));
                }
            }

            if (fishQ.size() == 0) {
                break;
            }

            Spot eatingFish = fishQ.poll();
            while (!fishQ.isEmpty()) {
                Spot compareFish = fishQ.poll();

                if (eatingFish.time > compareFish.time) {
                    eatingFish = compareFish;
                }

                if (eatingFish.time == compareFish.time) {
                    if (eatingFish.x > compareFish.x) {
                        eatingFish = compareFish;
                    }

                    if (eatingFish.x == compareFish.x && eatingFish.y > compareFish.y) {
                        eatingFish = compareFish;
                    }
                }
            }

            totalTime += eatingFish.time;
            eatingCount += 1;
            if (size == eatingCount) {
                size += 1;
                eatingCount = 0;
            }

            sea[tempShark.x][tempShark.y] = 0;
            sea[eatingFish.x][eatingFish.y] = 9;
            tempShark = eatingFish;
            q.offer(new Spot(eatingFish.x, eatingFish.y, 0));
        }
    }
}
