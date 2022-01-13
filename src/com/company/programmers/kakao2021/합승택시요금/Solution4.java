package com.company.programmers.kakao2021.합승택시요금;

import java.util.*;

public class Solution4 {
    Map<Integer, List<Node>> map = new HashMap<>();

    static class Node {
        int des;
        int dis;

        public Node(int des, int dis) {
            this.des = des;
            this.dis = dis;
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        initMap(fares);

        int[] distanceS = dijkstra(n, s);
        int[] distanceA = dijkstra(n, a);
        int[] distanceB = dijkstra(n, b);

        int minDis = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            minDis = Math.min(minDis, distanceS[i] + distanceA[i] + distanceB[i]);
        }

        return minDis;
    }

    private int[] dijkstra(int n, int start) {
        int[] distances = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[start] = 0;

        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n2 -> n2.dis));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (distances[node.des] < node.dis) {
                continue;
            }

            for (Node nextNode : map.get(node.des)) {
                int newDis = node.dis + nextNode.dis;
                if (newDis < distances[nextNode.des]) {
                    distances[nextNode.des] = newDis;
                    pq.offer(new Node(nextNode.des, newDis));
                }
            }
        }

        return distances;
    }

    private void initMap(int[][] fares) {
        for (int[] fare : fares) {
            if (map.containsKey(fare[0])) {
                map.get(fare[0]).add(new Node(fare[1], fare[2]));
            } else {
                List<Node> nodeList = new ArrayList<>();
                nodeList.add(new Node(fare[1], fare[2]));
                map.put(fare[0], nodeList);
            }

            if (map.containsKey(fare[1])) {
                map.get(fare[1]).add(new Node(fare[0], fare[2]));
            } else {
                List<Node> nodeList = new ArrayList<>();
                nodeList.add(new Node(fare[0], fare[2]));
                map.put(fare[1], nodeList);
            }
        }
    }

    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        System.out.println(solution.solution(6, 4, 6, 2,
                new int[][]
                        {
                                {4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}
                        }
        ));
//        System.out.println(solution.solution(7, 3, 4, 1,
//                new int[][]{
//                        {5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}
//                }
//        ));
//        System.out.println(solution.solution(6, 4, 5, 6,
//                new int[][]{
//                        {2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}
//                }
//        ));
    }
}
