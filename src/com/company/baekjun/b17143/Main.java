package com.company.baekjun.b17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c, m;
    static int[][] sea;
    static Map<Integer, Shark> sharkMap = new HashMap<>();
    static int totalFishingSize = 0;
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

        for (int i = 1; i <= c; i++) {
            fishing(i);
            moveSharks();
        }

        System.out.println(totalFishingSize);
    }

    private static void fishing(int col) {
        for (int i = 1; i <= r; i++) {
            if (sea[i][col] != 0) {
                int size = sea[i][col];
                totalFishingSize += size;
                sharkMap.remove(size);
                sea[i][col] = 0;
                break;
            }
        }
    }

    private static void moveSharks() {
        int[][] seaTemp = new int[r + 1][c + 1];
        List<Shark> loserSharks = new ArrayList<>();

        for (Integer sharkSize : sharkMap.keySet()) {
            Shark shark = sharkMap.get(sharkSize);
            sea[shark.x][shark.y] = 0;

            if (shark.dir == 1 || shark.dir == 2) { // 움직이는 횟수 줄이기
                shark.speed = shark.speed % (2 * (r - 1));
            } else {
                shark.speed = shark.speed % (2 * (c - 1));
            }

            int nx = shark.x;
            int ny = shark.y;
            for (int i = 0; i < shark.speed; i++) {
                if (nx == 1 && shark.dir == 1) {
                    shark.dir = 2;
                } else if (nx == r && shark.dir == 2) {
                    shark.dir = 1;
                } else if (ny == c && shark.dir == 3) {
                    shark.dir = 4;
                } else if (ny == 1 && shark.dir == 4) {
                    shark.dir = 3;
                }

                nx += dx[shark.dir];
                ny += dy[shark.dir];
            }

            shark.x = nx;
            shark.y = ny;

            if (seaTemp[shark.x][shark.y] != 0) {
                Shark sharkCompare = sharkMap.get(seaTemp[shark.x][shark.y]);
                if (shark.size > sharkCompare.size) {
                    seaTemp[shark.x][shark.y] = shark.size;
                    loserSharks.add(sharkCompare);
                } else if (shark.size < sharkCompare.size) {
                    loserSharks.add(shark);
                }
            } else {
                seaTemp[shark.x][shark.y] = shark.size;
            }
        }

        for (Shark loserShark : loserSharks) {
            sharkMap.remove(loserShark.size);
        }

        for (Shark shark : sharkMap.values()) {
            sea[shark.x][shark.y] = shark.size;
        }
    }
}