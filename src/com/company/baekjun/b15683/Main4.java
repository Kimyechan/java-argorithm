package com.company.baekjun.b15683;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main4 {
    static int n;
    static int m;
    static int[][] map;
    static List<Node> cctvs = new ArrayList<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][][] move = {
            {{0}, {1}, {2}, {3}},
            {{0, 1}, {2, 3}},
            {{0, 2}, {0, 3}, {1, 2}, {1, 3}},
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
            {{0, 1, 2, 3}}
    };
    static int minCount = Integer.MAX_VALUE;

    static class Node {
        int x;
        int y;
        int num;

        public Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(s[j]);
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new Node(i, j, map[i][j]));
                }
            }
        }

        recursive(0);

        System.out.println(minCount);
    }

    private static void recursive(int idx) {
        if (cctvs.size() <= idx) {
            minCount = Math.min(minCount, countNotFill());
            return;
        }

        Node cctv = cctvs.get(idx);
        for (int[] direction : move[cctv.num - 1]) {
            for (int d : direction) {
                markNotView(cctv.x, cctv.y, d, true);
            }

            recursive(idx + 1);

            for (int d : direction) {
                markNotView(cctv.x, cctv.y, d, false);
            }
        }
    }

    private static void markNotView(int x, int y, int dir, boolean isFill) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];


        while (true) {
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                break;
            }

            if (map[nx][ny] == 6) {
                break;
            }

            if (map[nx][ny] <= 0) {
                if (isFill) {
                    map[nx][ny] -= 1;
                } else {
                    map[nx][ny] += 1;
                }
            }

            nx += dx[dir];
            ny += dy[dir];
        }
    }

    private static int countNotFill() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    count += 1;
                }
            }
        }

        return count;
    }
}
