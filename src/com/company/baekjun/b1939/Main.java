package com.company.baekjun.b1939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static Map<Integer, List<Edge>> edgeMap = new HashMap<>();
    static boolean[] visited;

    static class Edge {
        int adj;
        int weight;

        public Edge(int adj, int weight) {
            this.adj = adj;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        for (int i = 1; i <= n; i++) {
            edgeMap.put(i, new ArrayList<>());
        }

        int left = 1000000000;
        int right = 1;
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            left = Math.min(left, weight);
            right = Math.max(right, weight);
            edgeMap.get(a).add(new Edge(b, weight));
            edgeMap.get(b).add(new Edge(a, weight));
        }

        input = br.readLine().split(" ");
        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);

        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            visited = new boolean[n + 1];
            if (checkPossible(start, end, mid)) {
                left = mid + 1;
                result = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }

    private static boolean checkPossible(int start, int end, int maxWeight) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int node = q.poll();

            if (node == end) {
                return true;
            }

            for (Edge edge : edgeMap.get(node)) {
                if (edge.weight < maxWeight || visited[edge.adj]) {
                    continue;
                }

                visited[edge.adj] = true;
                q.offer(edge.adj);
            }
        }

        return false;
    }
}