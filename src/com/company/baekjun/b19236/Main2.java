package com.company.baekjun.b19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main2 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxEatSum = 0;

    static class Shark {
        int x;
        int y;
        int dir;
        int eatSum;

        public Shark(int x, int y, int dir, int eatSum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }

    static class Fish {
        int x;
        int y;
        int number;
        int dir;
        boolean isAlive;

        public Fish(int x, int y, int number, int dir, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] board = new int[4][4];
        List<Fish> fishList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                int number = Integer.parseInt(input[j * 2]);
                int dir = Integer.parseInt(input[j * 2 + 1]) - 1;
                board[i][j] = number;
                fishList.add(new Fish(i, j, number, dir, true));
            }
        }

        fishList.sort(Comparator.comparingInt(f -> f.number));

        Fish firstFish = fishList.get(board[0][0] - 1);
        board[0][0] = 0;
        firstFish.isAlive = false;
        Shark shark = new Shark(0, 0, firstFish.dir, firstFish.number);

        dfs(board, fishList, shark);

        System.out.println(maxEatSum);
    }

    private static void dfs(int[][] board, List<Fish> fishList, Shark shark) {
        if (maxEatSum < shark.eatSum) {
            maxEatSum = shark.eatSum;
        }

        moveFishes(board, fishList, shark);

        for (int i = 1; i <= 3; i++) {
            int nx = shark.x + dx[shark.dir] * i;
            int ny = shark.y + dy[shark.dir] * i;

            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && board[nx][ny] != 0) {
                int[][] boardTemp = copyBoard(board);
                List<Fish> fishListTemp = copyFishList(fishList);

                int fishNumber = boardTemp[nx][ny];
                boardTemp[nx][ny] = 0;
                Fish deadFish = fishListTemp.get(fishNumber - 1);
                deadFish.isAlive = false;

                Shark nextShark = new Shark(nx, ny, deadFish.dir, shark.eatSum + deadFish.number);

                dfs(boardTemp, fishListTemp, nextShark);
            }
        }
    }

    private static List<Fish> copyFishList(List<Fish> fishList) {
        List<Fish> fishListTemp = new ArrayList<>();
        for (Fish fish : fishList) {
            fishListTemp.add(new Fish(fish.x, fish.y, fish.number, fish.dir, fish.isAlive));
        }

        return fishListTemp;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] boardTemp = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(board[i], 0, boardTemp[i], 0, 4);
        }

        return  boardTemp;
    }

    private static void moveFishes(int[][] board, List<Fish> fishList, Shark shark) {
        for (Fish fish : fishList) {
            if (!fish.isAlive) {
                continue;
            }

            int nx = 0;
            int ny = 0;
            int nDir = 0;

            for (int i = 0; i < 8; i++) {
                nDir = (fish.dir + i) % 8;
                nx = fish.x + dx[nDir];
                ny = fish.y + dy[nDir];

                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
                    continue;
                }

                if (nx == shark.x && ny == shark.y) {
                    continue;
                }

                if (board[nx][ny] == 0) {
                    board[fish.x][fish.y] = 0;
                } else {
                    Fish fishSwap = fishList.get(board[nx][ny] - 1);
                    board[fish.x][fish.y] = fishSwap.number;
                    fishSwap.x = fish.x;
                    fishSwap.y = fish.y;
                }
                board[nx][ny] = fish.number;
                fish.x = nx;
                fish.y = ny;
                fish.dir = nDir;

                break;
            }
        }
    }
}