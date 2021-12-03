package com.company.baekjun.b17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main3 {
    static int n, m, d;
    static int[][] board, copyBoard;
    static boolean[][] visited;
    static int maxKillCount = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m];
        copyBoard = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                copyBoard[i][j] = board[i][j];
            }
        }

        combination(0, new ArrayList<>());

        System.out.println(maxKillCount);
    }

    private static void combination(int start, List<Integer> archers) {
        if (archers.size() == 3) {
            initBoard();
            attack(archers);
            return;
        }

        for (int i = start; i < m; i++) {
            archers.add(i);
            combination(i + 1, archers);
            archers.remove(archers.size() - 1);
        }
    }

    private static void initBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = copyBoard[i][j];
            }
        }
    }

    private static void attack(List<Integer> archers) {
        int killCount = 0;

        for (int i = 0; i < n; i++) {
            for (Integer archer : archers) {
                int archerX = n;
                int archerY = archer;
                int minDis = Integer.MAX_VALUE;
                int x = 0;
                int y = 0;
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < m; k++) {
                        if (board[j][k] != 1) {
                            continue;
                        }

                        int dis = calcDistance(archerX, archerY, j, k);
                        if (dis < minDis) {
                            minDis = dis;
                            x = j;
                            y = k;
                        } else if (dis == minDis && y > k) {
                            x = j;
                            y = k;
                        }
                    }
                }

                if (minDis <= d) {
                    visited[x][y] = true;
                }
            }

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (visited[j][k]) {
                        killCount++;
                        board[j][k] = 0;
                        visited[j][k] = false;
                    }
                }
            }

            moveDown();
        }

        maxKillCount = Math.max(maxKillCount, killCount);
    }

    private static void moveDown() {
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                board[i + 1][j] = board[i][j];
            }
        }

        for (int i = 0; i < m; i++) {
            board[0][i] = 0;
        }
    }

    private static int calcDistance(int archerX, int archerY, int x, int y) {
        return Math.abs(archerX - x) + Math.abs(archerY - y);
    }
}