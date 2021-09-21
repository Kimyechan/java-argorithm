package com.company.programmers.kakao2021.합승택시요금;

import java.util.*;

class Solution3 {
    static Map<Integer, List<Node>> map = new HashMap<>();

    static class Node {
        int num;
        int dis;

        public Node(int num, int dis) {
            this.num = num;
            this.dis = dis;
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int dis = fare[2];
            if (map.containsKey(start)) {
                map.get(start).add(new Node(end, dis));
            } else {
                List<Node> nodeList = new ArrayList<>();
                nodeList.add(new Node(end, dis));
                map.put(start, nodeList);
            }

            if (map.containsKey(end)) {
                map.get(end).add(new Node(start, dis));
            } else {
                List<Node> nodeList = new ArrayList<>();
                nodeList.add(new Node(start, dis));
                map.put(end, nodeList);
            }
        }

        int[] distanceS = dijkstra(n, s);
        int[] distanceA = dijkstra(n, a);
        int[] distanceB = dijkstra(n, b);

        int minDis = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            minDis = Math.min(minDis, distanceS[i] + distanceA[i] + distanceB[i]);
        }

        return minDis;
    }

    public int[] dijkstra(int n, int s) {
        int[] distances = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[s] = 0;

        Queue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.dis - n2.dis);
        pq.offer(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (distances[node.num] < node.dis) {
                continue;
            }

            for (Node nextNode : map.getOrDefault(node.num, new ArrayList<>())) {
                int dis = node.dis + nextNode.dis;
                if (distances[nextNode.num] > dis) {
                    distances[nextNode.num] = dis;
                    pq.offer(new Node(nextNode.num, dis));
                }
            }
        }

        return distances;
    }
}