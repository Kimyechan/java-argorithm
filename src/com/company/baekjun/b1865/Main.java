package com.company.baekjun.b1865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge {
        private int adj;
        private int weight;

        public Edge(int adj, int weight) {
            this.adj = adj;
            this.weight = weight;
        }
    }

    private static Map<Integer, List<Edge>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            for (int i = 1; i < n + 1; i++) {
                graph.put(i, new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                Edge edge1 = new Edge(n2, weight);
                List<Edge> edges1 = graph.get(n1);
                edges1.add(edge1);
                graph.put(n1, edges1);

                Edge edge2 = new Edge(n1, weight);
                List<Edge> edges2 = graph.get(n2);
                edges2.add(edge2);
                graph.put(n2, edges2);
            }

            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                Edge edge1 = new Edge(n2, -weight);
                List<Edge> edges1 = graph.get(n1);
                edges1.add(edge1);
                graph.put(n1, edges1);
            }

            bellmanFord(n);
        }
    }

    private static void bellmanFord(int n) {
        long[] distance = new long[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n + 1; j++) {
                for (Edge e : graph.get(j)) {
                    if (distance[e.adj] > e.weight + distance[j]) {
                        distance[e.adj] = e.weight + distance[j];
                        if (i == n - 1) {
                            System.out.println("YES");
                            return;
                        }
                    }
                }
            }
        }

        System.out.println("NO");
    }
}
