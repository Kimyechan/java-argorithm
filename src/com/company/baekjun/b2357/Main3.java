package com.company.baekjun.b2357;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static int n, m;
    static long[] numbers;
    static int[][] arranges;
    static long[] maxTree;
    static long[] minTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        arranges = new int[m][2];
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            arranges[i][0] = Integer.parseInt(input[0]);
            arranges[i][1] = Integer.parseInt(input[1]);
        }

        minTree = new long[n * 4];
        maxTree = new long[n * 4];
        initMinTree(0, n - 1, 1);
        initMaxTree(0, n - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int left = arranges[i][0];
            int right = arranges[i][1];

            long minValue = findMinValue(0, n - 1, 1, left - 1, right - 1);
            long maxValue = findMaxValue(0, n - 1, 1, left - 1, right - 1);

            sb.append(minValue).append(" ").append(maxValue).append("\n");
        }

        System.out.println(sb);
    }

    private static long findMaxValue(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return Long.MIN_VALUE;
        }

        if (left <= start && right >= end) {
            return maxTree[node];
        }

        int mid = (start + end) / 2;
        return Math.max(findMaxValue(start, mid, node * 2, left, right), findMaxValue(mid + 1, end, node * 2 + 1, left, right));
    }

    private static long findMinValue(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return Long.MAX_VALUE;
        }

        if (left <= start && right >= end) {
            return minTree[node];
        }

        int mid = (start + end) / 2;
        return Math.min(findMinValue(start, mid, node * 2, left, right), findMinValue(mid + 1, end, node * 2 + 1, left, right));
    }

    private static long initMaxTree(int start, int end, int node) {
        if (start == end) {
            return maxTree[node] = numbers[start];
        }

        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(initMaxTree(start, mid, node * 2), initMaxTree(mid + 1, end, node * 2 + 1));
    }

    private static long initMinTree(int start, int end, int node) {
        if (start == end) {
            return minTree[node] = numbers[start];
        }

        int mid = (start + end) / 2;
        return minTree[node] = Math.min(initMinTree(start, mid, node * 2), initMinTree(mid + 1, end, node * 2 + 1));
    }
}