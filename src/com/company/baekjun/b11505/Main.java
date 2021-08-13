package com.company.baekjun.b11505;

import java.io.*;

public class Main {
    static int n, m, k;
    static int[] numbers;
    static long[] tree;
    static final long DIV_NUMBER = 1000000007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        tree = new long[n * 4];
        initTree(0, n - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + k; i++) {
            input = br.readLine().split(" ");
            if (input[0].equals("1")) {
                int idx = Integer.parseInt(input[1]) - 1;
                int changeNumber = Integer.parseInt(input[2]);
                numbers[idx] = changeNumber;
                update(0, n - 1, 1, idx, changeNumber);

            } else {
                int left = Integer.parseInt(input[1]) - 1;
                int right = Integer.parseInt(input[2]) - 1;
                long multipleValue = findTree(0, n - 1, 1, left, right);
                sb.append(multipleValue).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static long initTree(int start, int end, int node) {
        if (start == end) {
            return tree[node] = numbers[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = (initTree(start, mid, node * 2) * initTree(mid + 1, end, node * 2 + 1)) % DIV_NUMBER;
    }

    private static long update(int start, int end, int node, int idx, int changeNumber) {
        if (idx < start || idx > end) {
            return tree[node];
        }

        if (start == end) {
            return tree[node] = changeNumber;
        }

        int mid = (start + end) / 2;
        return tree[node] = (update(start, mid, node * 2, idx, changeNumber) * update(mid + 1, end, node * 2 + 1, idx, changeNumber)) % DIV_NUMBER;
    }

    private static long findTree(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 1;
        }

        if (left <= start && right >= end) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return (findTree(start, mid, node * 2, left, right) * findTree(mid + 1, end, node * 2 + 1, left, right)) % DIV_NUMBER;
    }
}