package com.company.baekjun.b1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    private static Map<Integer, List<Node>> tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            tree.put(i, new ArrayList<>());
        }

        for (int i = 1; i < n + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());

            int adj = 0;
            int weight = 0;
            int index = 0;
            while (true) {
                int value = Integer.parseInt(st.nextToken());

                if (value == -1) {
                    break;
                }

                if (index % 2 == 0) {
                    adj = value;
                } else {
                    weight = value;
                    List<Node> temp = tree.get(node);
                    temp.add(new Node(adj, weight));
                    tree.put(node, temp);
                }
                index += 1;
            }
        }

        Map<Integer, List<Node>> treeTemp = tree;
        Node firstNode = bfs(1, n);
        Node lastNode = bfs(firstNode.node, n);

        System.out.println(lastNode.weight);
    }

    private static Node bfs(int start, int n) {
        int[] visited = new int[n + 1];
        int[] dist = new int[n + 1];
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(new Node(start, 0));

        int maxDis = 0;
        int maxNode = 0;
        while (deque.size() != 0) {
            Node node = deque.poll();
            visited[node.node] = 1;

            for (Node nextNode : tree.get(node.node)) {
                if (visited[nextNode.node] == 0) {
                    dist[nextNode.node] = node.weight + nextNode.weight;
                    visited[nextNode.node] = 1;
                    deque.offer(new Node(nextNode.node, dist[nextNode.node]));
                    if (maxDis < dist[nextNode.node]) {
                        maxDis = dist[nextNode.node];
                        maxNode = nextNode.node;
                    }
                }
            }
        }
        return new Node(maxNode, maxDis);
    }
}
