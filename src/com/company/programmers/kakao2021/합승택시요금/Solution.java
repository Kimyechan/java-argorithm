package com.company.programmers.kakao2021.합승택시요금;

import java.util.*;

public class Solution {
    static Map<Integer, List<Node>> nodeMap = new HashMap<>();

    static class Node {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        for (int[] fare : fares) {
            int n1 = fare[0];
            int n2 = fare[1];
            int weight = fare[2];
            addNodeInfo(n1, n2, weight);
            addNodeInfo(n2, n1, weight);
        }

        int[] distanceS = dijsktra(n, s);
        int[] distanceA = dijsktra(n, a);
        int[] distanceB = dijsktra(n, b);
        int minDis = Integer.MAX_VALUE;

        for (int i = 1; i < n + 1; i++) {
            int cost = distanceS[i] + distanceA[i] + distanceB[i];
            minDis = Math.min(minDis, cost);
        }

        answer = minDis;

        return answer;
    }

    private int[] dijsktra(int size, int start) {
        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));

        int[] distances = new int[size + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (distances[node.num] < node.weight) {
                continue;
            }

            for (Node nextNode : nodeMap.getOrDefault(node.num, new ArrayList<>())) {
                int nextDis = node.weight + nextNode.weight;

                if (nextDis < distances[nextNode.num]) {
                    distances[nextNode.num] = nextDis;
                    pq.offer(new Node(nextNode.num, nextDis));
                }
            }
        }

        return distances;
    }

    private void addNodeInfo(int n1, int n2, int weight) {
        if (nodeMap.containsKey(n1)) {
            nodeMap.get(n1).add(new Node(n2, weight));
        } else {
            List<Node> nodeList = new ArrayList<>();
            nodeList.add(new Node(n2, weight));
            nodeMap.put(n1, nodeList);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.solution(6, 4, 6, 2,
//                new int[][]
//                        {
//                                {4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}
//                        }
//        ));
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