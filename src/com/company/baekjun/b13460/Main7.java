package com.company.baekjun.b13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main7 {
    static class Ball {
        int redX;
        int redY;
        int blueX;
        int blueY;
        int count;

        public Ball(int redX, int redY, int blueX, int blueY, int count) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.count = count;
        }
    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        String[][] board = initBoard(br, n, m);
        Ball ball = initRedBlueBall(n, m, board);

        boolean[][][][] visited = new boolean[n][m][n][m];

        int result = bfs(ball, board, visited);

        System.out.println(result);
    }

    private static int bfs(Ball ball, String[][] board, boolean[][][][] visited) {
        Queue<Ball> q = new LinkedList<>();
        q.add(ball);
        visited[ball.redX][ball.redY][ball.blueX][ball.blueY] = true;

        while (!q.isEmpty()) {
            Ball nextBall = q.poll();

            if (nextBall.count >= 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int nrx = nextBall.redX;
                int nry = nextBall.redY;
                while (!board[nrx + dx[i]][nry + dy[i]].equals("#")) {
                    nrx += dx[i];
                    nry += dy[i];

                    if (board[nrx][nry].equals("O")) {
                        break;
                    }
                }

                int nbx = nextBall.blueX;
                int nby = nextBall.blueY;
                while (!board[nbx + dx[i]][nby + dy[i]].equals("#")) {
                    nbx += dx[i];
                    nby += dy[i];

                    if (board[nbx][nby].equals("O")) {
                        break;
                    }
                }

                if (board[nbx][nby].equals("O")) {
                    continue;
                }

                if (board[nrx][nry].equals("O")) {
                    return nextBall.count + 1;
                }

                if (nrx == nbx && nry == nby) {
                    if (i == 0) {
                        if (nextBall.redY < nextBall.blueY) {
                            nry -= 1;
                        } else {
                            nby -= 1;
                        }
                    } else if (i == 1) {
                        if (nextBall.redY < nextBall.blueY) {
                            nby += 1;
                        } else {
                            nry += 1;
                        }
                    } else if (i == 2) {
                        if (nextBall.redX < nextBall.blueX) {
                            nrx -= 1;
                        } else {
                            nbx -= 1;
                        }
                    } else {
                        if (nextBall.redX < nextBall.blueX) {
                            nbx += 1;
                        } else {
                            nrx += 1;
                        }
                    }
                }

                if (visited[nrx][nry][nbx][nby]) {
                    continue;
                }

                visited[nrx][nry][nbx][nby] = true;
                q.offer(new Ball(nrx, nry, nbx, nby, nextBall.count + 1));
            }
        }

        return -1;
    }

    private static Ball initRedBlueBall(int n, int m, String[][] board) {
        int redX = 0;
        int redY = 0;
        int blueX = 0;
        int blueY = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j].equals("R")) {
                    redX = i;
                    redY = j;
                }
                if (board[i][j].equals("B")) {
                    blueX = i;
                    blueY = j;
                }
            }
        }

        return new Ball(redX, redY, blueX, blueY, 0);
    }

    private static String[][] initBoard(BufferedReader br, int n, int m) throws IOException {
        String[] line;
        String[][] board = new String[n][m];
        for (int i = 0; i < n; i++) {
            line = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = line[j];
            }
        }
        return board;
    }
}
