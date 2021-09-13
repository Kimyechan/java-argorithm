package com.company.baekjun.b4256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] pre;
    static int[] in;
    static StringBuilder postSB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        int n = 0;
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            pre = new int[n];
            in = new int[n];

            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                pre[j] = Integer.parseInt(input[j]);
            }

            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                in[j] = Integer.parseInt(input[j]);
            }

            postSB = new StringBuilder();
            recursionPostOrder(0, 0, n);

            System.out.println(postSB);
        }
    }

    private static void recursionPostOrder(int rootIdx, int start, int end) {
        if (rootIdx >= pre.length) {
            return;
        }

        int root = pre[rootIdx];
        for (int i = start; i < end; i++) {
            if (in[i] == root) {
                recursionPostOrder(rootIdx + 1, start, i);
                recursionPostOrder(rootIdx + i - start + 1, i + 1, end);
                postSB.append(root).append(" ");
            }
        }
    }
}