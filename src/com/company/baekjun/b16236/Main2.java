package com.company.baekjun.b16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main2 {
    private static int[] dX = {1, -1, 0, 0};
    private static int[] dY = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Shark shark = null;
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int size = 2;
        int count = 0;
        int totalTime = 0;

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                if (map[i][j] == 9) {
                    shark = new Shark(i, j, 0);
                }
            }
        }
        Deque<Shark> deque = new ArrayDeque<>();
        deque.offerLast(shark);

        while (true) {
            Deque<Shark> fishes = new ArrayDeque<>();
            int[][] dist = new int[n][n];
            Shark firstShark = deque.getFirst();
            dist[firstShark.x][firstShark.y] = -1;

            while (!deque.isEmpty()) {
                Shark nextShark = deque.pollFirst();

                for (int i = 0; i < 4; i++) {
                    int nx = nextShark.x + dX[i];
                    int ny = nextShark.y + dY[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }

                    if (map[nx][ny] > size || dist[nx][ny] != 0) {
                        continue;
                    }

                    if (map[nx][ny] != 0 && map[nx][ny] < size) {
                        fishes.add(new Shark(nx, ny, nextShark.moveTime + 1));
                    }

                    dist[nx][ny] = dist[nextShark.x][nextShark.y] + 1;
                    deque.offerLast(new Shark(nx, ny, nextShark.moveTime + 1));
                }
            }

            if (fishes.isEmpty()) {
                System.out.println(totalTime);
                break;
            }

            Shark eatingFish = fishes.pollFirst();
            while (!fishes.isEmpty()) {
                Shark compareFish = fishes.pollFirst();
                if (eatingFish.moveTime > compareFish.moveTime) {
                    eatingFish = compareFish;
                    continue;
                }

                if (eatingFish.moveTime == compareFish.moveTime) {
                    if (eatingFish.x > compareFish.x) {
                        eatingFish = compareFish;
                    } else if (eatingFish.x == compareFish.x) {
                        if (eatingFish.y > compareFish.y) {
                            eatingFish = compareFish;
                        }
                    }
                }
            }

            totalTime += eatingFish.moveTime;
            count += 1;
            if (count == size) {
                count = 0;
                size += 1;
            }

            map[shark.x][shark.y] = 0;
            map[eatingFish.x][eatingFish.y] = 9;
            shark = eatingFish;

            deque.offerFirst(new Shark(eatingFish.x, eatingFish.y, 0));
        }
    }

    static class Shark {
        int x;
        int y;
        int moveTime;

        public Shark(int x, int y, int moveTime) {
            this.x = x;
            this.y = y;
            this.moveTime = moveTime;
        }
    }
}
