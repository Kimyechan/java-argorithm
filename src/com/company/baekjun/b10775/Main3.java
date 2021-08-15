package com.company.baekjun.b10775;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static int g, p;
    static int[] airplane;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());

        airplane = new int[p];
        for (int i = 0; i < p; i++) {
            airplane[i] = Integer.parseInt(br.readLine());
        }

        parents = new int[g + 1];
        for (int i = 0; i < g + 1; i++) {
            parents[i] = i;
        }

        int parkingCount = 0;
        for (int i = 0; i < p; i++) {
            int maxGateNum = airplane[i];

            int parkingGateNum = find(maxGateNum);
            if (parkingGateNum == 0) {
                break;
            }

            union(parkingGateNum, parkingGateNum - 1);
            parkingCount++;
        }

        System.out.println(parkingCount);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parents[x] = y;
        }
    }

    private static int find(int num) {
        if (parents[num] != num) {
            return parents[num] = find(parents[num]);
        }

        return parents[num];
    }
}