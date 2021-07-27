package com.company.baekjun.b13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main4 {
    private static int n;
    private static int m;
    private static char[][] board;
    private static boolean[][][][] visited;
    private static int[] dX = {0, -1, 0, 1};
    private static int[] dY = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        int rX = 0;
        int rY = 0;
        int bX = 0;
        int bY = 0;
        board = new char[n][m];
        visited = new boolean[10][10][10][10];

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
        Spot initialSpot = new Spot(rX, rY, bX, bY, 0);
        int result = bfs(initialSpot);

        System.out.println(result);
    }

    private static int bfs(Spot spot) {
        Deque<Spot> deque = new ArrayDeque<>();
        deque.offerFirst(spot);

        while (!deque.isEmpty()) {
            Spot nSpot = deque.pollFirst();
            if (nSpot.cnt >= 10) {
                return -1;
            }

            if (visited[nSpot.rX][nSpot.rY][nSpot.bX][nSpot.bY]) {
                continue;
            }

            visited[nSpot.rX][nSpot.rY][nSpot.bX][nSpot.bY] = true;

            for (int dir = 0; dir < 4; dir++) {
                // 파란공 먼저 이동
                int nbX = nSpot.bX;
                int nbY = nSpot.bY;
                while (board[nbX + dX[dir]][nbY + dY[dir]] != '#') {
                    nbX += dX[dir];
                    nbY += dY[dir];
                    if (board[nbX][nbY] == 'O') {
                        break;
                    }
                }

                int nrX = nSpot.rX;
                int nrY = nSpot.rY;
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
                    return nSpot.cnt + 1;
                }

                if (nbX == nrX && nbY == nrY) {
                    switch (dir) {
                        case 0: // 서
                            if (nSpot.rY > nSpot.bY) {
                                nrY += 1;
                            } else {
                                nbY += 1;
                            }
                            break;
                        case 1: // 북
                            if (nSpot.rX > nSpot.bX) {
                                nrX += 1;
                            } else {
                                nbX += 1;
                            }
                            break;
                        case 2: // 동
                            if (nSpot.rY > nSpot.bY) {
                                nbY -= 1;
                            } else {
                                nrY -= 1;
                            }
                            break;
                        case 3: // 남
                            if (nSpot.rX > nSpot.bX) {
                                nbX -= 1;
                            } else {
                                nrX -= 1;
                            }
                            break;
                    }
                }

                if (!visited[nrX][nrY][nbX][nbY]) {
                    deque.offerLast(new Spot(nrX, nrY, nbX, nbY, nSpot.cnt + 1));
                }
            }
        }

        return -1;
    }

    static class Spot {
        int rX;
        int rY;
        int bX;
        int bY;
        int cnt;

        public Spot(int rX, int rY, int bX, int bY, int cnt) {
            this.rX = rX;
            this.rY = rY;
            this.bX = bX;
            this.bY = bY;
            this.cnt = cnt;
        }
    }
}
