package com.company.baekjun.b2357;

import java.io.*;

public class Main2 {
    static int n, m;
    static int[] numbers;
    static int[][] arranges;
    static int[] minTree;
    static int[] maxTree;

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

        arranges = new int[m][2];
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            arranges[i][0] = Integer.parseInt(input[0]);
            arranges[i][1] = Integer.parseInt(input[1]);
        }

        minTree = new int[numbers.length * 4];
        maxTree = new int[numbers.length * 4];

        initMinTree(0, numbers.length - 1, 1);
        initMaxTree(0, numbers.length - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int left = arranges[i][0];
            int right = arranges[i][1];

            int minNumber = findMinNumber(0, numbers.length - 1, 1, left - 1, right - 1);
            int maxNumber = findMaxNumber(0, numbers.length - 1, 1, left - 1, right - 1);
            sb.append(minNumber).append(" ").append(maxNumber).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static int initMinTree(int start, int end, int node) {
        if (start == end) {
            return minTree[node] = numbers[start];
        }

        int mid = (start + end) / 2;
        return minTree[node] = Math.min(initMinTree(start, mid, node * 2), initMinTree(mid + 1, end, node * 2 + 1));
    }

    private static int initMaxTree(int start, int end, int node) {
        if (start == end) {
            return maxTree[node] = numbers[start];
        }

        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(initMaxTree(start, mid, node * 2), initMaxTree(mid + 1, end, node * 2 + 1));
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

    private static int findMaxNumber(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return Integer.MIN_VALUE;
        }

        if (left <= start && right >= end) {
            return maxTree[node];
        }

        int mid = (start + end) / 2;
        return Math.max(findMaxNumber(start, mid, node * 2, left, right), findMaxNumber(mid + 1, end, node * 2 + 1, left, right));
    }
}