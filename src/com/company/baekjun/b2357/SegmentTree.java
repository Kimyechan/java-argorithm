package com.company.baekjun.b2357;

public class SegmentTree {
    static final int NUMBER_SIZE = 12;
    static int[] a = {1, 9, 3, 8, 4, 5, 5, 9, 10, 3, 4, 5};
    static int[] tree = new int[NUMBER_SIZE * 4];

    public static void main(String[] args) {
        init(0, NUMBER_SIZE - 1, 1);

        System.out.println(sum(0, NUMBER_SIZE - 1, 1, 0, 12));
        System.out.println(sum(0, NUMBER_SIZE - 1, 1, 3, 8));
        update(0, NUMBER_SIZE - 1, 1, 5, -5);
        System.out.println(sum(0, NUMBER_SIZE - 1, 1, 3, 8));
    }

    public static int init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = a[start];
        }
        int mid = (start + end) / 2;

        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    public static int sum(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    public static void update(int start, int end, int node, int index, int diff) {
        if (index < start || index > end) {
            return;
        }

        tree[node] += diff;
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }
}
