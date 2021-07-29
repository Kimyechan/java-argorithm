package com.company.baekjun.b16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main3 {
    private static int n;
    private static int[][] map;
    private static int[] dX = {0, 0, 1, -1};
    private static int[] dY = {1, -1, 0, 0};
    private static int eatingCount;
    private static int size = 2;
    private static int totalTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        Shark startShark = new Shark();

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(row[j]);
                if (map[i][j] == 9) {
                    startShark = new Shark(i, j, 0);
                }
            }
        }

        bfs(startShark);

        System.out.println(totalTime);
    }

    private static void bfs(Shark startShark) {
        Deque<Shark> deque = new ArrayDeque<>();
        deque.offerLast(startShark);
        Shark sharkTemp = startShark;
        Deque<Shark> fishes = new ArrayDeque<>();

        while (true) {
            boolean[][] visited = new boolean[n][n];
            Shark firstShark = deque.peekFirst();
            visited[firstShark.x][firstShark.y] = true;

            while (!deque.isEmpty()) {
                Shark nextShark = deque.pollFirst();

                for (int i = 0; i < 4; i++) {
                    int nx = nextShark.x + dX[i];
                    int ny = nextShark.y + dY[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }

                    if (map[nx][ny] > size) {
                        continue;
                    }

                    if (visited[nx][ny]) {
                        continue;
                    }

                    if (map[nx][ny] != 0 && map[nx][ny] < size) {
                        fishes.offerLast(new Shark(nx, ny, nextShark.moveTime + 1));
                    }

                    visited[nx][ny] = true;
                    deque.offerLast(new Shark(nx, ny, nextShark.moveTime + 1));
                }
            }

            if (fishes.isEmpty()) {
                break;
            }

            Shark eatingFish = findEatingShark(fishes);

            totalTime += eatingFish.moveTime;
            eatingCount += 1;
            if (eatingCount == size) {
                eatingCount = 0;
                size += 1;
            }

            map[eatingFish.x][eatingFish.y] = 9;
            map[sharkTemp.x][sharkTemp.y] = 0;
            sharkTemp = new Shark(eatingFish.x, eatingFish.y, 0);
            deque.offerLast(new Shark(eatingFish.x, eatingFish.y, 0));
        }
    }

    private static Shark findEatingShark(Deque<Shark> fishes) {
        Shark eatingFish = fishes.pollFirst();
        while (!fishes.isEmpty()) {
            Shark compareFish = fishes.pollFirst();

            if (eatingFish.moveTime > compareFish.moveTime) {
                eatingFish = compareFish;
            }

            if (eatingFish.moveTime == compareFish.moveTime) {
                if (eatingFish.x > compareFish.x) {
                    eatingFish = compareFish;
                }

                if (eatingFish.x == compareFish.x && eatingFish.y > compareFish.y) {
                    eatingFish = compareFish;
                }
            }
        }

        return eatingFish;
    }

    static class Shark {
        int x;
        int y;
        int moveTime;

        public Shark() {
        }

        public Shark(int x, int y, int moveTime) {
            this.x = x;
            this.y = y;
            this.moveTime = moveTime;
        }
    }
}
