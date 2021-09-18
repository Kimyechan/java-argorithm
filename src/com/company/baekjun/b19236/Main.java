package com.company.baekjun.b19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxCount = 0;

    static class Shark {
        int x;
        int y;
        int eatCount;
        int dir;

        public Shark(int x, int y, int eatCount, int dir) {
            this.x = x;
            this.y = y;
            this.eatCount = eatCount;
            this.dir = dir;
        }
    }

    static class Fish {
        int x;
        int y;
        int number;
        int dir;
        boolean isALive;

        public Fish(int x, int y, int number, int dir, boolean isALive) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.dir = dir;
            this.isALive = isALive;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] fishes = new int[4][4];
        List<Fish> fishList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                int number = Integer.parseInt(input[j * 2]);
                int dir = Integer.parseInt(input[j * 2 + 1]) - 1;
                fishes[i][j] = number;
                fishList.add(new Fish(i, j, number, dir, true));
            }
        }

        fishList.sort(Comparator.comparingInt(f -> f.number));
        Fish fish = fishList.get(fishes[0][0] - 1);
        fish.isALive = false;
        Shark shark = new Shark(0, 0, fish.number, fish.dir);
        fishes[0][0] = 0;

        dfs(fishes, shark, fishList);

        System.out.println(maxCount);
    }

    private static void dfs(int[][] fishes, Shark shark, List<Fish> fishList) {
        if (maxCount < shark.eatCount) {
            maxCount = shark.eatCount;
        }

        moveFish(fishes, shark, fishList);

        for (int i = 1; i <= 3; i++) {
            int nx = shark.x + dx[shark.dir] * i;
            int ny = shark.y + dy[shark.dir] * i;

            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && fishes[nx][ny] != 0) {
                List<Fish> fishListCopy = copyFishList(fishList);
                int[][] newFishes = copyFishes(fishes);

                Fish nextFish = fishListCopy.get(fishes[nx][ny] - 1);
                nextFish.isALive = false;
                newFishes[nx][ny] = 0;
                Shark nextShark = new Shark(nx, ny, shark.eatCount + nextFish.number, nextFish.dir);

                dfs(newFishes, nextShark, fishListCopy);
            }
        }
    }

    private static int[][] copyFishes(int[][] fishes) {
        int[][] newFishes = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newFishes[i][j] = fishes[i][j];
            }
        }

        return newFishes;
    }

    private static List<Fish> copyFishList(List<Fish> fishList) {
        List<Fish> copyList = new ArrayList<>();
        for (Fish fish : fishList) {
            copyList.add(new Fish(fish.x, fish.y, fish.number, fish.dir, fish.isALive));
        }

        return copyList;
    }

    private static void moveFish(int[][] fishes, Shark shark, List<Fish> fishList) {
        for (Fish fish : fishList) {
            if (!fish.isALive) {
                continue;
            }

            for (int i = 0; i < 8; i++) {
                int nextDir = (fish.dir + i) % 8;
                int nx = fish.x + dx[nextDir];
                int ny = fish.y + dy[nextDir];


                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
                    continue;
                }

                if (nx == shark.x && ny == shark.y) {
                    continue;
                }

                if (fishes[nx][ny] == 0) {
                    fishes[fish.x][fish.y] = 0;
                } else {
                    Fish swapFish = fishList.get(fishes[nx][ny] - 1);
                    fishes[fish.x][fish.y] = swapFish.number;
                    swapFish.x = fish.x;
                    swapFish.y = fish.y;
                }

                fishes[nx][ny] = fish.number;
                fish.dir = nextDir;
                fish.x = nx;
                fish.y = ny;

                break;
            }
        }
    }
}