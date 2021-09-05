package com.company.programmers.kakao2021.합승택시요금;

import java.util.*;

public class Solution2 {
    Map<Integer, List<Node>> fareMap = new HashMap<>();

    static class Node {
        int adj;
        int weight;

        public Node(int adj, int weight) {
            this.adj = adj;
            this.weight = weight;
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        for (int[] fare : fares) {
            insertFareMap(fare[0], fare[1], fare[2]);
            insertFareMap(fare[1], fare[0], fare[2]);
        }

        int[] distanceS = dijkstra(s, n);
        int[] distanceA = dijkstra(a, n);
        int[] distanceB = dijkstra(b, n);

        int minDis = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            minDis = Math.min(minDis, distanceS[i] + distanceA[i] + distanceB[i]);
        }

        answer = minDis;

        return answer;
    }

    private int[] dijkstra(int start, int n) {
        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n2 -> n2.weight));
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (distances[node.adj] < node.weight) {
                continue;
            }

            for (Node nextNode : fareMap.getOrDefault(node.adj, new ArrayList<>())) {
                int dis = node.weight + nextNode.weight;
                if (distances[nextNode.adj] > dis) {
                    distances[nextNode.adj] = dis;
                    pq.offer(new Node(nextNode.adj, dis));
                }
            }
        }

        return distances;
    }

    private void insertFareMap(int node, int adj, int weight) {
        if (fareMap.containsKey(node)) {
            fareMap.get(node).add(new Node(adj, weight));
        } else {
            List<Node> nodeList = new ArrayList<>();
            nodeList.add(new Node(adj, weight));
            fareMap.put(node, nodeList);
        }
    }
}