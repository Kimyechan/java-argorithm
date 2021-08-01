package com.company.baekjun.b15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main5 {
    static int n;
    static int m;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][][] viewDirection = {
            {{0}, {1}, {2}, {3}},
            {{0, 1}, {2, 3}},
            {{0, 2}, {1, 2}, {0, 3}, {1, 3}},
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
            {{0, 1, 2, 3}}
    };
    static int[][] room;
    static List<CCTV> cctvs = new ArrayList<>();
    static int minNotView = Integer.MAX_VALUE;

    static class CCTV {
        int x;
        int y;
        int number;

        public CCTV(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        m = Integer.parseInt(row[1]);

        room = new int[n][m];
        for (int i = 0; i < n; i++) {
            row = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(row[j]);
                if (room[i][j] >= 1 && room[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, room[i][j]));
                }
            }
        }

        recursive(0);
        System.out.println(minNotView);
    }

    private static void recursive(int idx) {
        if (idx == cctvs.size()) {
            minNotView = Math.min(minNotView, countNotView());
            return;
        }

        CCTV cctv = cctvs.get(idx);
        for (int[] dir : viewDirection[cctv.number - 1]) {
            for (int d : dir) {
                markNotView(cctv.x, cctv.y, d, false);
            }

            recursive(idx + 1);

            for (int d : dir) {
                markNotView(cctv.x, cctv.y, d, true);
            }
        }
    }

    private static int countNotView() {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (room[i][j] == 0) {
                    count += 1;
                }
            }
        }

        return count;
    }

    private static void markNotView(int x, int y, int d, boolean isVisible) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        while(true) {
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                break;
            }

            if (room[nx][ny] == 6) {
                break;
            }

            if (room[nx][ny] <= 0) {
                if (isVisible) {
                    room[nx][ny] += 1;
                } else {
                    room[nx][ny] -= 1;
                }
            }

            nx += dx[d];
            ny += dy[d];
        }
    }
}
