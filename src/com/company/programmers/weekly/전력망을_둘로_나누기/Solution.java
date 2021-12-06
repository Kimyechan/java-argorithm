package com.company.programmers.weekly.전력망을_둘로_나누기;

import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < wires.length; i++) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int j = 1; j <= n; j++) {
                map.put(j, new ArrayList<>());
            }
            for (int j = 0; j < wires.length; j++) {
                if (i == j) {
                    continue;
                }
                map.get(wires[j][0]).add(wires[j][1]);
                map.get(wires[j][1]).add(wires[j][0]);
            }
            List<Integer> sizeList = new ArrayList<>();
            boolean[] visited = new boolean[n + 1];
            for (int j = 1; j <= n; j++) {
                if (visited[j]) {
                    continue;
                }

                int count = bfs(j, visited, map);
                sizeList.add(count);
            }

            answer = Math.min(answer, Math.abs(sizeList.get(0) - sizeList.get(1)));
        }

        return answer;
    }

    public int bfs(int start, boolean[] visited, Map<Integer, List<Integer>> map) {
        int count = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        visited[start] = true;

        while (!q.isEmpty()) {
            Integer node = q.poll();

            count += 1;

            for (Integer num : map.get(node)) {
                if (visited[num]) {
                    continue;
                }

                visited[num] = true;
                q.offer(num);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(9,	new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}));
    }
}

