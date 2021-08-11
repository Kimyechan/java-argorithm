package com.company.baekjun.b10775;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static int g, p;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());

        parents = new int[g + 1];
        for (int i = 0; i < g + 1; i++) {
            parents[i] = i;
        }

        int dockingCount = 0;
        for (int i = 0; i < p; i++) {
            int maxGate = Integer.parseInt(br.readLine());

            int dockingGate = find(maxGate);
            if (dockingGate == 0) {
                break;
            }

            union(dockingGate, dockingGate - 1);
            dockingCount++;
        }

        System.out.println(dockingCount);
    }

    private static int find(int gate) {
        if (parents[gate] != gate) {
            parents[gate] = find(parents[gate]);
        }
        return parents[gate];
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parents[x] = y;
        }
    }
}