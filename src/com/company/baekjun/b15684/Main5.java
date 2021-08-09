package com.company.baekjun.b15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main5 {
    static int n, m, h;
    static int[][] bridge;
    static boolean isFinish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        m = Integer.parseInt(row[1]);
        h = Integer.parseInt(row[2]);

        bridge = new int[h + 1][n + 1];
        for (int i = 0; i < m; i++) {
            row = br.readLine().split(" ");
            int a = Integer.parseInt(row[0]);
            int b = Integer.parseInt(row[1]);

            bridge[a][b] = 1;
            bridge[a][b + 1] = -1;
        }

        int ans = 0;
        for (int i = 0; i <= 3; i++) {
            recursive(1, i, 0);
            if (isFinish) {
                ans = i;
                break;
            }
        }

        ans = isFinish ? ans : -1;
        System.out.println(ans);
    }

    private static void recursive(int row, int limit, int count) {
        if (isFinish) {
            return;
        }

        if (count == limit) {
            if (checkLine()) {
                isFinish = true;
            }
            return;
        }

        for (int i = row; i < h + 1; i++) {
            for (int j = 1; j < n; j++) {
                if (bridge[i][j] != 0 || bridge[i][j + 1] != 0) {
                    continue;
                }

                bridge[i][j] = 1;
                bridge[i][j + 1] = -1;
                recursive(i, limit, count + 1);
                bridge[i][j] = 0;
                bridge[i][j + 1] = 0;
            }
        }
    }

    private static boolean checkLine() {
        for (int i = 1; i < n + 1; i++) {
            int line = i;
            for (int j = 1; j < h + 1; j++) {
                if (bridge[j][line] == 1) {
                    line += 1;
                } else if (bridge[j][line] == -1) {
                    line -= 1;
                }
            }

            if (line != i) {
                return false;
            }
        }

        return true;
    }
}