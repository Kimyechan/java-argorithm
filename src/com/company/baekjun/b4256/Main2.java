package com.company.baekjun.b4256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static int[] pre;
    static int[] in;
    static StringBuilder post = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            String[] preInput = br.readLine().split(" ");
            pre = new int[n];
            for (int j = 0; j < n; j++) {
                pre[j] = Integer.parseInt(preInput[j]);
            }

            String[] inInput = br.readLine().split(" ");
            in = new int[n];
            for (int j = 0; j < n; j++) {
                in[j] = Integer.parseInt(inInput[j]);
            }

            makePostOrder(0, 0, n);
            post.append("\n");
        }

        System.out.println(post.toString());
    }

    private static void makePostOrder(int rootIdx, int start, int end) {
        if (rootIdx >= pre.length) {
            return;
        }

        int root = pre[rootIdx];
        for (int i = start; i < end; i++) {
            if (in[i] == root) {
                makePostOrder(rootIdx + 1, start, i);
                makePostOrder(rootIdx + i - start + 1, i + 1, end);
                post.append(root).append(" ");
            }
        }
    }
}
