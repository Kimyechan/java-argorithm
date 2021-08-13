package com.company.baekjun.b10868;

import java.io.*;

public class Main {
    static int n, m;
    static int[] numbers;
    static int[] minTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        minTree = new int[n * 4];
        initMinNumber(0, n - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] arranges = br.readLine().split(" ");
            int left = Integer.parseInt(arranges[0]);
            int right = Integer.parseInt(arranges[1]);

            int minNumber = findMinNumber(0, n - 1, 1, left - 1, right - 1);
            sb.append(minNumber).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int initMinNumber(int start, int end, int node) {
        if (start == end) {
            return minTree[node] = numbers[start];
        }

        int mid = (start + end) / 2;
        return minTree[node] = Math.min(initMinNumber(start, mid, node * 2), initMinNumber(mid + 1, end, node * 2 + 1));
    }

    private static int findMinNumber(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        }

        if (left <= start && right >= end) {
            return minTree[node];
        }

        int mid = (start + end) / 2;
        return Math.min(findMinNumber(start, mid, node * 2, left, right), findMinNumber(mid + 1, end, node * 2 + 1, left, right));
    }
}