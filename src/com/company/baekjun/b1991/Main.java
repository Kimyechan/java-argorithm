package com.company.baekjun.b1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        String node;
        String left;
        String right;

        public Node(String node, String left, String right) {
            this.node = node;
            this.left = left;
            this.right = right;
        }
    }

    private static Map<String, Node> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String node = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            tree.put(node, new Node(node, left, right));
        }

        preOrder("A");
        System.out.println();
        inOrder("A");
        System.out.println();
        postOrder("A");
    }

    public static void preOrder(String node) {
        if (!tree.containsKey(node)) {
            return;
        }

        System.out.print(node);
        preOrder(tree.get(node).left);
        preOrder(tree.get(node).right);
    }

    public static void inOrder(String node) {
        if (!tree.containsKey(node)) {
            return;
        }

        inOrder(tree.get(node).left);
        System.out.print(node);
        inOrder(tree.get(node).right);
    }

    public static void postOrder(String node) {
        if (!tree.containsKey(node)) {
            return;
        }

        postOrder(tree.get(node).left);
        postOrder(tree.get(node).right);
        System.out.print(node);
    }
}
