package com.company.baekjun.b1939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
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
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        for (int i = 1; i <= n; i++) {
            edgeMap.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int aLand = Integer.parseInt(input[0]);
            int bLand = Integer.parseInt(input[1]);
            int weightLimit = Integer.parseInt(input[2]);

            edgeMap.get(aLand).add(new Edge(bLand, weightLimit));
            edgeMap.get(bLand).add(new Edge(aLand, weightLimit));
        }

        input = br.readLine().split(" ");
        int startingPoint = Integer.parseInt(input[0]);
        int destination = Integer.parseInt(input[1]);

        int result = 0;
        int start = 1;
        int end = 1000000000;
        while (start <= end) {
            int mid = (start + end) / 2;

            visited = new boolean[n + 1];
            if (checkPossibleWeight(startingPoint, destination, mid)) {
                start = mid + 1;
                result = mid;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }

    private static boolean checkPossibleWeight(int startingPoint, int destination, int weight) {
        boolean result = false;

        Queue<Integer> q = new LinkedList<>();
        q.offer(startingPoint);
        visited[startingPoint] = true;

        while (!q.isEmpty()) {
            int land = q.poll();

            if (land == destination) {
                result = true;
                break;
            }

            for (Edge edge : edgeMap.get(land)) {
                if (edge.weight < weight || visited[edge.adj]) {
                    continue;
                }

                visited[edge.adj] = true;
                q.offer(edge.adj);
            }
        }

        return result;
    }
}