package com.company.baekjun.b17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2 {
    static int r, c, m;
    static int[][] sea;
    static Map<Integer, Shark> sharkMap = new HashMap<>();
    static int totalFishingSize;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};

    static class Shark {
        int x;
        int y;
        int speed;
        int dir;
        int size;

        public Shark(int x, int y, int speed, int dir, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        r = Integer.parseInt(row[0]);
        c = Integer.parseInt(row[1]);
        m = Integer.parseInt(row[2]);

        sea = new int[r + 1][c + 1];

        for (int i = 0; i < m; i++) {
            row = br.readLine().split(" ");
            int x = Integer.parseInt(row[0]);
            int y = Integer.parseInt(row[1]);
            int speed = Integer.parseInt(row[2]);
            int dir = Integer.parseInt(row[3]);
            int size = Integer.parseInt(row[4]);

            sea[x][y] = size;
            sharkMap.put(size, new Shark(x, y, speed, dir, size));
        }

        for (int col = 1; col <= c; col++) {
            fishing(col);
            moveSharks();
        }

        System.out.println(totalFishingSize);
    }

    private static void fishing(int col) {
        for (int i = 1; i <= r; i++) {
            if (sea[i][col] != 0) {
                int size = sea[i][col];
                sharkMap.remove(size);
                totalFishingSize += size;
                break;
            }
        }
    }

    private static void moveSharks() {
        int[][] seaTemp = new int[r + 1][c + 1];
        List<Integer> loserSharks = new ArrayList<>();

        for (Integer sharkSize : sharkMap.keySet()) {
            Shark shark = sharkMap.get(sharkSize);

            int nx = shark.x;
            int ny = shark.y;
            if (shark.dir == 1 || shark.dir == 2) {
                shark.speed %= 2 * (r - 1);
            } else {
                shark.speed %= 2 * (c - 1);
            }

            for (int i = 0; i < shark.speed; i++) {
                if (shark.dir == 1 && nx == 1) {
                    shark.dir = 2;
                } else if (shark.dir == 2 && nx == r) {
                    shark.dir = 1;
                } else if (shark.dir == 3 && ny == c) {
                    shark.dir = 4;
                } else if (shark.dir == 4 && ny == 1) {
                    shark.dir = 3;
                }

                nx += dx[shark.dir];
                ny += dy[shark.dir];
            }

            sea[shark.x][shark.y] = 0;
            shark.x = nx;
            shark.y = ny;
            if (seaTemp[shark.x][shark.y] != 0) {
                if (seaTemp[shark.x][shark.y] > sharkSize) {
                    loserSharks.add(sharkSize);
                } else {
                    loserSharks.add(seaTemp[shark.x][shark.y]);
                    seaTemp[shark.x][shark.y] = sharkSize;
                }
            } else {
                seaTemp[shark.x][shark.y] = sharkSize;
            }
        }

        for (Integer loserSharkSize : loserSharks) {
            sharkMap.remove(loserSharkSize);
        }

        for (Shark survivedShark : sharkMap.values()) {
            sea[survivedShark.x][survivedShark.y] = survivedShark.size;
        }
    }
}