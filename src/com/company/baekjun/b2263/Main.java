package com.company.baekjun.b2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[] inOrder;
    private static int[] postOrder;
    private static int[] index;
    private static List<Integer> preOrder = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        inOrder = new int[n];
        postOrder = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        index = new int[n + 1];
        for (int i = 0; i < n; i++) {
            index[inOrder[i]] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        makePreOrder(0, n - 1, 0, n - 1);

        for (Integer node : preOrder) {
            System.out.print(node + " ");
        }
    }

    private static void makePreOrder(int inOrderLeft, int inOrderRight, int postOrderLeft, int postOrderRight) {
        if (inOrderLeft > inOrderRight || postOrderLeft > postOrderRight) {
            return;
        }

        int root = postOrder[postOrderRight];
        preOrder.add(root);
        int rootIndex = index[root];
        int leftLen = rootIndex - inOrderLeft;

        makePreOrder(inOrderLeft, rootIndex - 1, postOrderLeft, postOrderLeft + leftLen - 1);
        makePreOrder(rootIndex + 1, inOrderRight, postOrderLeft + leftLen, postOrderRight - 1);
    }
}
