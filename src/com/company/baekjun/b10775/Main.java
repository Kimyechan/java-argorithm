package com.company.baekjun.b10775;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int g, p;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());

        parent = new int[g + 1];
        for (int i = 0; i < g + 1; i++) {
            parent[i] = i;
        }

        int maxDockingCount = 0;
        for (int i = 0; i < p; i++) {
            int maxGateNum = Integer.parseInt(br.readLine());

            int dockingNum = find(maxGateNum);
            if (dockingNum == 0) {
                break;
            }

            maxDockingCount++;
            union(dockingNum, dockingNum - 1);
        }

        System.out.println(maxDockingCount);
    }

    private static int find(int num) {
        if (parent[num] != num) {
            parent[num] = find(parent[num]);
        }
        return parent[num];
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[x] = y;
        }
    }
}