package com.company.baekjun.b14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[][] board;
    private static int[] commands;
    private static int[] dice = new int[6];
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    private static int x;
    private static int y;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        x = Integer.parseInt(input[2]);
        y = Integer.parseInt(input[3]);
        int k = Integer.parseInt(input[4]);

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        commands = new int[k];
        input = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            commands[i] = Integer.parseInt(input[i]);
        }

        for (int i = 0; i < k; i++) {
            moveDice(commands[i] - 1);
        }
    }

    public static void moveDice(int direction) {
        int nx = x + dx[direction];
        int ny = y + dy[direction];

        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            return;
        }

        int[] temp = dice.clone();
        if (direction == 0) {
            dice[0] = temp[3];
            dice[2] = temp[0];
            dice[5] = temp[2];
            dice[3] = temp[5];
        } else if (direction == 1) {
            dice[0] = temp[2];
            dice[2] = temp[5];
            dice[5] = temp[3];
            dice[3] = temp[0];
        } else if (direction == 2) {
            dice[0] = temp[4];
            dice[4] = temp[5];
            dice[5] = temp[1];
            dice[1] = temp[0];
        } else if (direction == 3) {
            dice[0] = temp[1];
            dice[4] = temp[0];
            dice[5] = temp[4];
            dice[1] = temp[5];
        }

        if (board[nx][ny] == 0) {
            board[nx][ny] = dice[5];
        } else {
            dice[5] = board[nx][ny];
            board[nx][ny] = 0;
        }

        x = nx;
        y = ny;

        System.out.println(dice[0]);
    }
}
