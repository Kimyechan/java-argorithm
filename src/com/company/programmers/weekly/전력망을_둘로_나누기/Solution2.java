package com.company.programmers.weekly.전력망을_둘로_나누기;

import java.util.*;

public class Solution2 {
    Map<Integer, List<Integer>> wireMap = new HashMap<>();
    boolean[] visited;
    boolean[][] connected;
    int minCount = Integer.MAX_VALUE;

    public int solution(int n, int[][] wires) {
        for (int i = 1; i <= n; i++) {
            wireMap.put(i, new ArrayList<>());
        }

        for (int[] wire : wires) {
            wireMap.get(wire[0]).add(wire[1]);
            wireMap.get(wire[1]).add(wire[0]);
        }

        visited = new boolean[n + 1];
        connected = new boolean[n + 1][n + 1];
        for (int[] wire : wires) {
            connected[wire[0]][wire[1]] = true;
            connected[wire[1]][wire[0]] = true;
        }

        for (int[] wire : wires) {
            connected[wire[0]][wire[1]] = false;
            connected[wire[1]][wire[0]] = false;
            int firstSize = bfs(1);
            int secondSize = 0;
            for (int i = 1; i < n + 1; i++) {
                if (visited[i]) {
                    continue;
                }

                secondSize = bfs(i);
                break;
            }
            visited = new boolean[n + 1];
            connected[wire[0]][wire[1]] = true;
            connected[wire[1]][wire[0]] = true;

            minCount = Math.min(minCount, Math.abs(firstSize - secondSize));
        }

        return minCount;
    }

    private int bfs(int start) {
        int count = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        visited[start] = true;

        while (!q.isEmpty()) {
            int next = q.poll();
            count++;

            for (int node : wireMap.get(next)) {
                if (visited[node] || !connected[next][node]) {
                    continue;
                }

                visited[node] = true;
                q.offer(node);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.solution(9,	new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}));
    }
}
