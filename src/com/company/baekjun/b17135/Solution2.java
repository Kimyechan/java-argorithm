package com.company.baekjun.b17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution2 {
    static int n, m, d;
    static int[][] map;
    static int[][] copyMap;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        copyMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                copyMap[i][j] = map[i][j];
            }
        }

        combination(0, m, new ArrayList<>());

        System.out.println(ans);
    }

    private static void combination(int start, int m, List<Integer> archers) {
        if (archers.size() == 3) {
            init();
            kill(archers);
            return;
        }

        for (int i = start; i < m; i++) {
            archers.add(i);
            combination(i + 1, m, archers);
            archers.remove(archers.size() - 1);
        }
    }

    private static void init() {
        for (int i = 0; i < n; i++) {
            if (m >= 0) System.arraycopy(copyMap[i], 0, map[i], 0, m);
        }
    }

    private static void kill(List<Integer> archers) {
        boolean[][] visited = new boolean[n][m];
        int killCount = 0;

        while (!checkFinish()) {
            for (Integer archer : archers) {
                int minDis = Integer.MAX_VALUE;
                int row = 0;
                int col = Integer.MAX_VALUE;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (map[i][j] != 1) {
                            continue;
                        }

                        int dis = calcDistance(i, j, n, archer);
                        if (dis > d) {
                            continue;
                        }

                        if (minDis > dis) {
                            minDis = dis;
                            row = i;
                            col = j;
                        } else if (minDis == dis && col > j) {
                            row = i;
                            col = j;
                        }
                    }
                }

                if (col == Integer.MAX_VALUE) {
                    continue;
                }

                visited[row][col] = true;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j]) {
                        killCount++;
                        map[i][j] = 0;
                    }
                }
            }

            for (int i = n - 1; i > 0; i--) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = map[i - 1][j];
                }
            }

            for (int i = 0; i < m; i++) {
                map[0][i] = 0;
            }
            visited = new boolean[n][m];
        }

        ans = Math.max(ans, killCount);
    }

    private static int calcDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static boolean checkFinish() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }
}