package com.company.baekjun.b11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static Deque<Integer> deque = new ArrayDeque<>();
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visited = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            List<Integer> nodes1 = graph.get(n1);
            nodes1.add(n2);
            List<Integer> nodes2 = graph.get(n2);
            nodes2.add(n1);
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0){
                bfs(i);
                result += 1;
            }
        }

        System.out.println(result);
    }

    private static void bfs(int node) {
        visited[node] = 1;
        deque.offer(node);

        while (deque.size() != 0) {
            int nextNode = deque.poll();

            for (Integer n : graph.get(nextNode)) {
                if (visited[n] == 0) {
                    visited[n] = 1;
                    deque.offer(n);
                }
            }
        }
    }
}
