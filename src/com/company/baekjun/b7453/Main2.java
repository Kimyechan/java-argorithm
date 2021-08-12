package com.company.baekjun.b7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
    static int n;
    static int[] A, B, C, D;
    static int[] AB, CD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            A[i] = Integer.parseInt(row[0]);
            B[i] = Integer.parseInt(row[1]);
            C[i] = Integer.parseInt(row[2]);
            D[i] = Integer.parseInt(row[3]);
        }

        AB = new int[n * n];
        CD = new int[n * n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx] = C[i] + D[j];
                idx++;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        long count = 0;
        int idxAB = 0;
        int idxCD = n * n - 1;
        while (idxAB < n * n && idxCD >= 0) {
            int sum = AB[idxAB] + CD[idxCD];

            if (sum == 0) {
                long abCount = 0;
                int tempABIdx = idxAB;
                while (tempABIdx < n * n && AB[idxAB] == AB[tempABIdx]) {
                    abCount++;
                    tempABIdx++;
                }
                idxAB = tempABIdx;

                long cdCount = 0;
                int tempCDIdx = idxCD;
                while (tempCDIdx >= 0 && CD[idxCD] == CD[tempCDIdx]) {
                    cdCount++;
                    tempCDIdx--;
                }
                idxCD = tempCDIdx;

                count += abCount * cdCount;
            } else if (sum > 0) {
                idxCD--;
            } else {
                idxAB++;
            }
        }

        System.out.println(count);
    }
}