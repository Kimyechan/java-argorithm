package com.company.baekjun.b16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// 틀린 방식 -> 동일한 거리에 대한 잘못된 우선순위 결정
public class Main {
    private static int n;
    private static int[][] map;
    private static int[] dX = {-1, 0, 1, 1};
    private static int[] dY = {0, -1, 0, 0};
    private static boolean[][] visited;
    private static int totalTime;
    private static int startX;
    private static int startY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        Shark shark = null;

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                if (map[i][j] == 9) {
                    startX = i;
                    startY = j;
                    shark = new Shark(i, j, 0, 2, 0);
                }
            }
        }
        bfs(shark);
        System.out.println(totalTime);
    }

    private static void bfs(Shark shark) {
        Deque<Shark> deque = new ArrayDeque<>();
        deque.offerLast(shark);

        while (!deque.isEmpty()) {
            Shark nextShark = deque.pollFirst();
            if (visited[nextShark.x][nextShark.y]) {
                continue;
            }
            visited[nextShark.x][nextShark.y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = nextShark.x + dX[i];
                int ny = nextShark.y + dY[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (map[nx][ny] > nextShark.size) {
                    continue;
                }

                if (map[nx][ny] != 0 && map[nx][ny] < nextShark.size) {
                    nextShark.eatingCount += 1;
                    if (nextShark.eatingCount == nextShark.size) {
                        nextShark.eatingCount = 0;
                        nextShark.size += 1;
                    }
                    nextShark.moveTime += 1;
                    totalTime = nextShark.moveTime;

                    map[startX][startY] = 0;
                    map[nx][ny] = 9;
                    startX = nx;
                    startY = ny;

                    deque.clear();
                    deque.offerLast(new Shark(startX, startY, nextShark.eatingCount, nextShark.size, nextShark.moveTime));

                    visited = new boolean[n][n];
                    break;
                }

                if (!visited[nx][ny]) {
                    deque.offerLast(new Shark(nx, ny, nextShark.eatingCount, nextShark.size, nextShark.moveTime + 1));
                }
            }
        }
    }

    static class Shark {
        int x;
        int y;
        int eatingCount;
        int size;
        int moveTime;

        public Shark(int x, int y, int eatingCount, int size, int moveTime) {
            this.x = x;
            this.y = y;
            this.eatingCount = eatingCount;
            this.size = size;
            this.moveTime = moveTime;
        }
    }
}
