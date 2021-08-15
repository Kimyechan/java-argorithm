package com.company.baekjun.b7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main4 {
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
            String[] input = br.readLine().split(" ");
            A[i] = Integer.parseInt(input[0]);
            B[i] = Integer.parseInt(input[1]);
            C[i] = Integer.parseInt(input[2]);
            D[i] = Integer.parseInt(input[3]);
        }

        AB = new int[n * n];
        CD = new int[n * n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx] = C[i] + D[j];
                idx += 1;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        int abIdx = 0;
        int cdIdx = n * n - 1;
        long count = 0;
        while (abIdx < n * n && cdIdx >= 0) {
            int sum = AB[abIdx] + CD[cdIdx];

            if (sum == 0) {
                int abIdxTemp = abIdx;
                long abCount = 0;
                while (abIdxTemp < n * n && AB[abIdx] == AB[abIdxTemp]) {
                    abCount++;
                    abIdxTemp++;
                }
                abIdx = abIdxTemp;

                int cdIdxTemp = cdIdx;
                long cdCount = 0;
                while (cdIdxTemp >= 0 && CD[cdIdx] == CD[cdIdxTemp]) {
                    cdCount++;
                    cdIdxTemp--;
                }
                cdIdx = cdIdxTemp;

                count += abCount * cdCount;
            } else if (sum < 0) {
                abIdx++;
            } else {
                cdIdx--;
            }
        }

        System.out.println(count);
    }
}