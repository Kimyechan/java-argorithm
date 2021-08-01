package com.company.baekjun.b13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main6 {
    static int n, m;
    static char[][] board;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Bead {
        int redX;
        int redY;
        int blueX;
        int blueY;
        int count;

        public Bead(int redX, int redY, int blueX, int blueY, int count) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        m = Integer.parseInt(row[1]);
        board = new char[n][m];
        int redX = 0;
        int redY = 0;
        int blueX = 0;
        int blueY = 0;

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                board[i][j] = line[j];
                if (board[i][j] == 'R') {
                    redX = i;
                    redY = j;
                }
                if (board[i][j] == 'B') {
                    blueX = i;
                    blueY = j;
                }
            }
        }

        Bead startBead = new Bead(redX, redY, blueX, blueY, 0);

        int minCount = moveBead(startBead);

        System.out.println(minCount);
    }

    private static int moveBead(Bead startBead) {
        Queue<Bead> q = new LinkedList<>();
        q.offer(startBead);

        boolean[][][][] visited = new boolean[10][10][10][10];
        visited[startBead.redX][startBead.redY][startBead.blueX][startBead.blueY] = true;

        while(!q.isEmpty()) {
            Bead nextBead = q.poll();

            if (nextBead.count >= 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                // 파란색 공 이동
                int blueNextX = nextBead.blueX;
                int blueNextY = nextBead.blueY;
                while (board[blueNextX + dx[i]][blueNextY + dy[i]] != '#') {
                    blueNextX += dx[i];
                    blueNextY += dy[i];

                    if (board[blueNextX][blueNextY] == 'O') {
                        break;
                    }
                }

                // 빨간색
                int redNextX = nextBead.redX;
                int redNextY = nextBead.redY;
                while (board[redNextX + dx[i]][redNextY + dy[i]] != '#') {
                    redNextX += dx[i];
                    redNextY += dy[i];

                    if (board[redNextX][redNextY] == 'O') {
                        break;
                    }
                }

                if (board[blueNextX][blueNextY] == 'O') {
                    continue;
                }

                if (board[redNextX][redNextY] == 'O') {
                    return nextBead.count + 1;
                }

                if (blueNextX == redNextX && blueNextY == redNextY) {
                    if (i == 0) { // 동
                        if (nextBead.redY > nextBead.blueY) {
                            blueNextY -= 1;
                        } else {
                            redNextY -= 1;
                        }
                    } else if (i == 1) { // 서
                        if (nextBead.redY > nextBead.blueY) {
                            redNextY += 1;
                        } else {
                            blueNextY += 1;
                        }
                    } else if (i == 2) { // 남
                        if (nextBead.redX > nextBead.blueX) {
                            blueNextX -= 1;
                        } else {
                            redNextX -= 1;
                        }
                    } else { // 북
                        if (nextBead.redX > nextBead.blueX) {
                            redNextX += 1;
                        } else {
                            blueNextX += 1;
                        }
                    }
                }

                if (visited[redNextX][redNextY][blueNextX][blueNextY]) {
                    continue;
                }

                visited[redNextX][redNextY][blueNextX][blueNextY] = true;
                q.offer(new Bead(redNextX, redNextY, blueNextX, blueNextY, nextBead.count + 1));
            }
        }

        return -1;
    }
}