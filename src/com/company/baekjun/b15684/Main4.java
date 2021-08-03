package com.company.baekjun.b15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {
    static int n, m, h;
    static int[][] map;
    static boolean isFinish;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        m = Integer.parseInt(row[1]);
        h = Integer.parseInt(row[2]);

        map = new int[h + 1][n + 1];
        for (int i = 0; i < m; i++) {
            row = br.readLine().split(" ");
            int x = Integer.parseInt(row[0]);
            int y = Integer.parseInt(row[1]);

            map[x][y] = 1;
            map[x][y + 1] = 2;
        }

        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(1, 0);
            if (isFinish) {
                break;
            }
        }

        answer = isFinish ? answer : -1;
        System.out.println(answer);
    }

    private static void dfs(int x, int count) {
        if (isFinish) {
            return;
        }

        if (answer == count) {
            if (check()) {
                isFinish = true;
            }
            return;
        }

        for (int i = x; i < h + 1; i++) {
            for (int j = 1; j < n; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
                    dfs(i, count + 1);
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean check() {
        for (int i = 1; i < n + 1; i++) {
            int x = 1;
            int y = i;
            for (int j = 1; j < h + 1; j++) {
                if (map[x][y] == 1) {
                    y++;
                } else if (map[x][y] == 2) {
                    y--;
                }
                x++;
            }
            if (y != i) {
                return false;
            }
        }
        return true;
    }
}
