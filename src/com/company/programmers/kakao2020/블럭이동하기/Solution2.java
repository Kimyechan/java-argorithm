package com.company.programmers.kakao2020.블럭이동하기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Robot {
        int fX;
        int fY;
        int sX;
        int sY;
        int time;

        public Robot(int fX, int fY, int sX, int sY, int time) {
            this.fX = fX;
            this.fY = fY;
            this.sX = sX;
            this.sY = sY;
            this.time = time;
        }
    }

    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;

        Queue<Robot> robotQueue = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][n][n][n];

        robotQueue.offer(new Robot(0, 0, 0, 1, 0));
        visited[0][0][0][1] = true;

        while (!robotQueue.isEmpty()) {
            Robot nextRobot = robotQueue.poll();

            if ((nextRobot.fX == n - 1 && nextRobot.fY == n - 1) || (nextRobot.sX == n - 1 && nextRobot.sY== n - 1)) {
                answer = nextRobot.time;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nFX = nextRobot.fX + dx[i];
                int nFY = nextRobot.fY + dy[i];
                int nSX = nextRobot.sX + dx[i];
                int nSY = nextRobot.sY + dy[i];

                if (nFX < 0 || nFX >= n || nFY < 0 || nFY >= n || nSX < 0 || nSX >= n || nSY < 0 || nSY >= n) {
                    continue;
                }

                if (board[nFX][nFY] == 1 || board[nSX][nSY] == 1) {
                    continue;
                }

                if (visited[nFX][nFY][nSX][nSY]) {
                    continue;
                }

                visited[nFX][nFY][nSX][nSY] = true;
                robotQueue.offer(new Robot(nFX, nFY, nSX, nSY, nextRobot.time + 1));

                if (nFX == nSX && (i == 0 || i == 1)) {
                    visited[nextRobot.fX][nextRobot.fY][nFX][nextRobot.fY] = true;
                    robotQueue.offer(new Robot(nextRobot.fX, nextRobot.fY, nFX, nextRobot.fY, nextRobot.time + 1));
                    visited[nextRobot.sX][nextRobot.sY][nSX][nextRobot.sY] = true;
                    robotQueue.offer(new Robot(nextRobot.sX, nextRobot.sY, nSX, nextRobot.sY, nextRobot.time + 1));
                }

                if (nFY == nSY && (i == 2 || i == 3)) {
                    visited[nextRobot.fX][nextRobot.fY][nextRobot.fX][nFY] = true;
                    robotQueue.offer(new Robot(nextRobot.fX, nextRobot.fY, nextRobot.fX, nFY, nextRobot.time + 1));
                    visited[nextRobot.sX][nextRobot.sY][nextRobot.sX][nSY] = true;
                    robotQueue.offer(new Robot(nextRobot.sX, nextRobot.sY, nextRobot.sX, nSY, nextRobot.time + 1));
                }
            }
        }

        return answer;
    }
}