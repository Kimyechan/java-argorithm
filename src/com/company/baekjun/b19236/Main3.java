package com.company.baekjun.b19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main3 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxEat = 0;

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

    static class Shark {
        int x;
        int y;
        int eatSum;
        int dir;

        public Shark(int x, int y, int eatSum, int dir) {
            this.x = x;
            this.y = y;
            this.eatSum = eatSum;
            this.dir = dir;
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

        Shark shark = new Shark(0, 0, firstFish.number, firstFish.dir);

        dfs(board, fishList, shark);

        System.out.println(maxEat);
    }

    private static void dfs(int[][] board, List<Fish> fishList, Shark shark) {
        for (Fish fish : fishList) {
            if (!fish.isAlive) {
                continue;
            }
            moveFish(board, fishList, fish, shark);
        }

        if (checkFinish(board, shark)) {
            maxEat = Math.max(maxEat, shark.eatSum);
            return;
        }

        int nx = shark.x;
        int ny = shark.y;
        for (int i = 0; i < 3; i++) {
            nx += dx[shark.dir];
            ny += dy[shark.dir];

            if ((nx >= 0 && nx < 4 && ny >= 0 && ny < 4) && board[nx][ny] != 0) {
                int[][] boardTemp = copyBoard(board);
                List<Fish> fishTempList = copyFishList(fishList);

                Fish fishEat = fishTempList.get(board[nx][ny] - 1);
                fishEat.isAlive = false;
                boardTemp[nx][ny] = 0;
                Shark nextShark = new Shark(nx, ny, shark.eatSum + fishEat.number, fishEat.dir);

                dfs(boardTemp, fishTempList, nextShark);
            }
        }
    }

    private static List<Fish> copyFishList(List<Fish> fishList) {
        List<Fish> fishTempList = new ArrayList<>();
        for (Fish fish : fishList) {
            fishTempList.add(new Fish(fish.x, fish.y, fish.number, fish.dir, fish.isAlive));
        }

        return fishTempList;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] boardTemp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boardTemp[i][j] = board[i][j];
            }
        }

        return boardTemp;
    }

    private static void moveFish(int[][] board, List<Fish> fishList, Fish fish, Shark shark) {
        int dir = fish.dir;
        for (int i = 0; i < 8; i++) {
            int nx = fish.x + dx[dir];
            int ny = fish.y + dy[dir];

            if ((nx >= 0 && nx < 4 && ny >= 0 && ny < 4) && !(shark.x == nx && shark.y == ny)) {
                if (board[nx][ny] == 0) {
                    board[nx][ny] = fish.number;
                    board[fish.x][fish.y] = 0;
                } else {
                    Fish fishChange = fishList.get(board[nx][ny] - 1);
                    board[fish.x][fish.y] = fishChange.number;
                    board[fishChange.x][fishChange.y] = fish.number;
                    fishChange.x = fish.x;
                    fishChange.y = fish.y;
                }
                fish.x = nx;
                fish.y = ny;
                fish.dir = dir;
                break;
            }

            dir = (dir + 1) % 8;
        }
    }

    private static boolean checkFinish(int[][] board, Shark shark) {
        int nx = shark.x + dx[shark.dir];
        int ny = shark.y + dy[shark.dir];

        while (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
            if (board[nx][ny] != 0) {
                return false;
            }
            nx += dx[shark.dir];
            ny += dy[shark.dir];
        }

        return true;
    }
}