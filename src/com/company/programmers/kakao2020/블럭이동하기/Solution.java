package com.company.programmers.kakao2020.블럭이동하기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static Queue<Robot> robotQ = new LinkedList<>();
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

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
        visited = new boolean[n][n][n][n];

        Robot robot = new Robot(0, 0, 0, 1, 0);
        visited[0][0][0][1] = true;
        robotQ.offer(robot);

        while (!robotQ.isEmpty()) {
            Robot nextRobot = robotQ.poll();

            if ((nextRobot.fX == n - 1 && nextRobot.fY == n - 1) || (nextRobot.sX == n - 1 && nextRobot.sY == n - 1)) {
                answer = nextRobot.time;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int fNX = nextRobot.fX + dx[i];
                int fNY = nextRobot.fY + dy[i];
                int sNX = nextRobot.sX + dx[i];
                int sNY = nextRobot.sY + dy[i];

                if (fNX < 0 || fNX >= n || fNY < 0 || fNY >= n || sNX < 0 || sNX >= n || sNY < 0 || sNY >= n) {
                    continue;
                }

                if (board[fNX][fNY] == 1 || board[sNX][sNY] == 1) {
                    continue;
                }

                if (visited[fNX][fNY][sNX][sNY]) {
                    continue;
                }

                visited[fNX][fNY][sNX][sNY] = true;
                robotQ.offer(new Robot(fNX, fNY, sNX, sNY, nextRobot.time + 1));

                if ((i == 0 || i == 1) && nextRobot.fX == nextRobot.sX) {
                    visited[sNX][sNY][nextRobot.sX][nextRobot.sY] = true;
                    robotQ.offer(new Robot(sNX, sNY, nextRobot.sX, nextRobot.sY, nextRobot.time + 1));

                    visited[fNX][fNY][nextRobot.fX][nextRobot.fY] = true;
                    robotQ.offer(new Robot(fNX, fNY, nextRobot.fX, nextRobot.fY, nextRobot.time + 1));
                }
                if ((i == 2|| i == 3) && nextRobot.fY == nextRobot.sY) {
                    visited[sNX][sNY][nextRobot.sX][nextRobot.sY] = true;
                    robotQ.offer(new Robot(sNX, sNY, nextRobot.sX, nextRobot.sY, nextRobot.time + 1));

                    visited[fNX][fNY][nextRobot.fX][nextRobot.fY] = true;
                    robotQ.offer(new Robot(fNX, fNY, nextRobot.fX, nextRobot.fY, nextRobot.time + 1));
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}
        }));
    }
}