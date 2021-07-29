package com.company.baekjun.b15683;

import java.io.*;
import java.util.*;

public class Main2 {
    static class Node {
        int row, col;
        int num;

        public Node(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
        }
    }

    static int n, m;
    static int[][] graph;
    static List<Node> cctvs = new ArrayList<>();
    static int[] moveRow = {0, -1, 0, 1};
    static int[] moveCol = {-1, 0, 1, 0};
    static int[][][] move = {
            {{}},
            {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}},
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
            {{0, 1, 2, 3}}
    };
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(s[j]);
                if (graph[i][j] >= 1 && graph[i][j] <= 5) {
                    cctvs.add(new Node(i, j, graph[i][j]));
                }
            }
        }

        recursive(0);
        System.out.println(result);
    }

    public static void recursive(int idx) {
        if (cctvs.size() <= idx) {
            result = Math.min(countBlindSpot(), result);
            return;
        }

        Node cctv = cctvs.get(idx);
        for (int[] directions : move[cctv.num]) {
            for (int d : directions) {
                fill(cctv.row, cctv.col, d, true);
            }

            recursive(idx + 1);

            for (int d : directions) {
                fill(cctv.row, cctv.col, d, false);
            }
        }
    }

    public static void fill(int sRow, int sCol, int direction, boolean isFill) {
        while (true) {
            int row = sRow + moveRow[direction];
            int col = sCol + moveCol[direction];
            if (!(row >= 0 && row < n && col >= 0 && col < m && graph[row][col] != 6)) {
                break;
            }

            if (graph[row][col] <= 0) {
                if (isFill) graph[row][col] -= 1;
                else graph[row][col] += 1;
            }
            sRow = row;
            sCol = col;
        }
    }

    public static int countBlindSpot() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0) count += 1;
            }
        }
        return count;
    }
}
