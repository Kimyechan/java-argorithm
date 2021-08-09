package com.company.baekjun.b17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main3 {
    static int r, c, m;
    static int[][] sea;
    static Map<Integer, Shark> sharkMap = new HashMap<>();
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};

    static class Shark {
        int x;
        int y;
        int s;
        int d;
        int z;

        public Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
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
            int s = Integer.parseInt(row[2]);
            int d = Integer.parseInt(row[3]);
            int z = Integer.parseInt(row[4]);

            sea[x][y] = z;
            sharkMap.put(z, new Shark(x, y, s, d, z));
        }

        int totalFishingSize = 0;
        for (int i = 1; i <= c; i++) {
            totalFishingSize += fishing(i);
            moveShark();
        }

        System.out.println(totalFishingSize);
    }

    private static int fishing(int col) {
        int fishingSize = 0;
        for (int i = 1; i <= r; i++) {
            if (sea[i][col] != 0) {
                fishingSize = sea[i][col];
                sharkMap.remove(sea[i][col]);
                sea[i][col] = 0;
                break;
            }
        }

        return fishingSize;
    }

    private static void moveShark() {
        int[][] seaTemp = new int[r + 1][c + 1];
        List<Shark> loserSharks = new ArrayList<>();

        for (Integer size : sharkMap.keySet()) {
            Shark shark = sharkMap.get(size);
            sea[shark.x][shark.y] = 0;

            int speed = shark.s;
            if (shark.d == 1 || shark.d == 2) {
                speed %= 2 * (r - 1);
            } else {
                speed %= 2 * (c - 1);
            }
            shark.s = speed;

            int nx = shark.x;
            int ny = shark.y;
            for (int i = 0; i < shark.s; i++) {
                if (nx == 1 && shark.d == 1) {
                    shark.d = 2;
                } else if (nx == r && shark.d == 2) {
                    shark.d = 1;
                } else if (ny == c && shark.d == 3) {
                    shark.d = 4;
                } else if (ny == 1 && shark.d == 4) {
                    shark.d = 3;
                }
                nx += dx[shark.d];
                ny += dy[shark.d];
            }
            shark.x = nx;
            shark.y = ny;

            if (seaTemp[shark.x][shark.y] != 0) {
                if (seaTemp[shark.x][shark.y] < shark.z) {
                    loserSharks.add(sharkMap.get(seaTemp[shark.x][shark.y]));
                    seaTemp[shark.x][shark.y] = shark.z;
                } else {
                    loserSharks.add(sharkMap.get(shark.z));
                }
            } else {
                seaTemp[shark.x][shark.y] = shark.z;
            }
        }

        for (Shark loserShark : loserSharks) {
            sharkMap.remove(loserShark.z);
        }

        for (Integer size : sharkMap.keySet()) {
            Shark shark = sharkMap.get(size);
            sea[shark.x][shark.y] = size;
        }
    }
}