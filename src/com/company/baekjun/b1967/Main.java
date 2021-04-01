package com.company.baekjun.b1967;


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

    private static List<List<Node>> tree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree.get(n1).add(new Node(n2, weight));
            tree.get(n2).add(new Node(n1, weight));
        }

        Node firstNode = findNode(1, n);
        Node lastNode = findNode(firstNode.node, n);

        System.out.println(lastNode.weight);
    }

    private static Node findNode(int start, int n) {
        int maxLen = 0;
        int maxVertex = 0;

        int[] visited = new int[n + 1];
        int[] distance = new int[n + 1];
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(new Node(start, 0));

        while (deque.size() != 0) {
            Node currentNode = deque.poll();
            visited[currentNode.node] = 1;

            for (Node nextNode : tree.get(currentNode.node)) {
                if (visited[nextNode.node] == 0) {
                    visited[nextNode.node] = 1;
                    distance[nextNode.node] = currentNode.weight + nextNode.weight;
                    deque.offer(new Node(nextNode.node, distance[nextNode.node]));
                    if (maxLen < distance[nextNode.node]) {
                        maxLen = distance[nextNode.node];
                        maxVertex = nextNode.node;
                    }
                }
            }
        }

        return new Node(maxVertex, maxLen);
    }
}
