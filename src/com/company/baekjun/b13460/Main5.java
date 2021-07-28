package com.company.baekjun.b13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main5 {
    private static int n;
    private static int m;
    private static char[][] board;
    private static boolean[][][][] visited;
    private static int[] dX = {0, 0, 1, -1};
    private static int[] dY = {1, -1, 0, 0};
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        board = new char[n][m];

        int rX = 0;
        int rY = 0;
        int bX = 0;
        int bY = 0;
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = row.charAt(j);
                if (board[i][j] == 'R') {
                    rX = i;
                    rY = j;
                } else if (board[i][j] == 'B') {
                    bX = i;
                    bY = j;
                }
            }
        }
        visited = new boolean[n][m][n][m];

        Bead startBead = new Bead(rX, rY, bX, bY, 0);
        bfs(startBead);

        System.out.println(count);
    }

    private static void bfs(Bead startBead) {
        Deque<Bead> deque = new ArrayDeque<>();
        deque.offerLast(startBead);
        visited[startBead.rX][startBead.rY][startBead.bX][startBead.bY] = true;

        while (!deque.isEmpty()) {
            Bead nextBead = deque.pollFirst();

            if (nextBead.cnt >= 10) {
                count = -1;
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nbX = nextBead.bX;
                int nbY = nextBead.bY;

                while (board[nbX + dX[dir]][nbY + dY[dir]] != '#') {
                    nbX += dX[dir];
                    nbY += dY[dir];
                    if (board[nbX][nbY] == 'O') {
                        break;
                    }
                }

                int nrX = nextBead.rX;
                int nrY = nextBead.rY;

                while (board[nrX + dX[dir]][nrY + dY[dir]] != '#') {
                    nrX += dX[dir];
                    nrY += dY[dir];
                    if (board[nrX][nrY] == 'O') {
                        break;
                    }
                }

                if (board[nbX][nbY] == 'O') {
                    continue;
                }

                if (board[nrX][nrY] == 'O') {
                    count = nextBead.cnt + 1;
                    return;
                }

                if (nrX == nbX && nrY == nbY) {
                    switch (dir) {
                        case 0: // 동
                            if (nextBead.rY > nextBead.bY) {
                                nbY -= 1;
                            } else {
                                nrY -= 1;
                            }
                            break;
                        case 1: // 서
                            if (nextBead.rY > nextBead.bY) {
                                nrY += 1;
                            } else {
                                nbY += 1;
                            }
                            break;
                        case 2: // 남
                            if (nextBead.rX > nextBead.bX) {
                                nbX -= 1;
                            } else {
                                nrX -= 1;
                            }
                            break;
                        case 3: // 북
                            if (nextBead.rX > nextBead.bX) {
                                nrX += 1;
                            } else {
                                nbX += 1;
                            }
                            break;
                    }
                }

                if (!visited[nrX][nrY][nbX][nbY]) {
                    visited[nrX][nrY][nbX][nbY] = true;
                    deque.offerLast(new Bead(nrX, nrY, nbX, nbY, nextBead.cnt + 1));
                }
            }
        }

        count = -1;
    }

    static class Bead {
        int rX;
        int rY;
        int bX;
        int bY;
        int cnt;

        public Bead(int rX, int rY, int bX, int bY, int cnt) {
            this.rX = rX;
            this.rY = rY;
            this.bX = bX;
            this.bY = bY;
            this.cnt = cnt;
        }
    }
}
