package com.company.baekjun.b14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {
    static int n;
    static int l;
    static int[][] roads;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        l = Integer.parseInt(row[1]);
        
        roads = new int[n][n];
        for (int i = 0; i < n; i++) {
            row = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                roads[i][j] = Integer.parseInt(row[j]);
            }
        }
        
        int totalPassRoad = 0;
        for (int i = 0; i < n; i++) {
            if (checkRoad(i, 0, true)) {
                totalPassRoad += 1;
            }
            
            if (checkRoad(0, i, false)) {
                totalPassRoad += 1;
            }
        }

        System.out.println(totalPassRoad);
    }

    private static boolean checkRoad(int x, int y, boolean isRow) {
        int[] roadLine = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            roadLine[i] = isRow ? roads[x][y + i] : roads[x + i][y];       
        }

        for (int i = 0; i < n - 1; i++) {
            if (roadLine[i] == roadLine[i + 1]) {
                continue;
            }

            if (Math.abs(roadLine[i] - roadLine[i + 1]) > 1) {
                return false;
            }

            if (roadLine[i] == roadLine[i + 1] + 1) {
                for (int j = i + 1; j < i + 1 + l; j++) {
                    if (j >= n || roadLine[i + 1] != roadLine[j] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            }

            if (roadLine[i] == roadLine[i + 1] - 1) {
                for (int j = i; j > i - l; j--) {
                    if (j < 0 || roadLine[i] != roadLine[j] || visited[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            }
        }

        return true;
    }
}
