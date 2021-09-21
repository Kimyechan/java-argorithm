package com.company.programmers.kakao2020.블럭이동하기;

import java.util.*;

class Solution3 {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Node {
        int x1;
        int y1;
        int x2;
        int y2;
        int time;

        public Node(int x1, int y1, int x2, int y2, int time) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.time = time;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;

        boolean[][][][] visited = new boolean[n][n][n][n];
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(0, 0, 0, 1, 0));
        visited[0][0][0][1] = true;

        int arriveTime = 0;
        while (!q.isEmpty()) {
            Node nextNode = q.poll();

            if ((nextNode.x1 == n - 1 && nextNode.y1 == n - 1) || (nextNode.x2 == n - 1 && nextNode.y2 == n - 1)) {
                arriveTime = nextNode.time;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx1 = nextNode.x1 + dx[i];
                int ny1 = nextNode.y1 + dy[i];
                int nx2 = nextNode.x2 + dx[i];
                int ny2 = nextNode.y2 + dy[i];

                if (nx1 < 0 || nx1 >= n || ny1 < 0 || ny1 >= n || nx2 < 0 || nx2 >= n || ny2 < 0 || ny2 >= n) {
                    continue;
                }

                if (board[nx1][ny1] == 1 || board[nx2][ny2] == 1) {
                    continue;
                }

                if (!visited[nx1][ny1][nx2][ny2]) {
                    visited[nx1][ny1][nx2][ny2] = true;
                    q.offer(new Node(nx1, ny1, nx2, ny2, nextNode.time + 1));
                }

                if ((i == 0 || i == 1) && nextNode.y1 == nextNode.y2) {
                    if (!visited[nextNode.x1][nextNode.y1][nx1][ny1]) {
                        visited[nextNode.x1][nextNode.y1][nx1][ny1] = true;
                        q.offer(new Node(nextNode.x1, nextNode.y1, nx1, ny1, nextNode.time + 1));
                    }
                    if (!visited[nextNode.x2][nextNode.y2][nx2][ny2]) {
                        visited[nextNode.x2][nextNode.y2][nx2][ny2] = true;
                        q.offer(new Node(nextNode.x2, nextNode.y2, nx2, ny2, nextNode.time + 1));
                    }
                }
                if ((i == 2 || i == 3) && nextNode.x1 == nextNode.x2) {
                    if (!visited[nextNode.x1][nextNode.y1][nx1][ny1]) {
                        visited[nextNode.x1][nextNode.y1][nx1][ny1] = true;
                        q.offer(new Node(nextNode.x1, nextNode.y1, nx1, ny1, nextNode.time + 1));
                    }
                    if (!visited[nextNode.x2][nextNode.y2][nx2][ny2]) {
                        visited[nextNode.x2][nextNode.y2][nx2][ny2] = true;
                        q.offer(new Node(nextNode.x2, nextNode.y2, nx2, ny2, nextNode.time + 1));
                    }
                }
            }
        }

        return arriveTime;
    }
}