package com.company.topologySort.최종순위;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> ranks = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                ranks.add(num);
            }

            Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
            int[] inDegree = new int[n + 1];

            for (int j = 0; j < n; j++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int k = j + 1; k < ranks.size(); k++) {
                    temp.add(ranks.get(k));
                }

                graph.put(ranks.get(j), temp);
                inDegree[ranks.get(j)] = j;
            }

            int m = Integer.parseInt(br.readLine());

            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (graph.get(a).contains(b)) {
                    ArrayList<Integer> aTemp = graph.get(a);
                    aTemp.remove((Integer) b);
                    graph.put(a, aTemp);
                    ArrayList<Integer> bTemp = graph.get(b);
                    bTemp.add(a);
                    graph.put(b, bTemp);

                    inDegree[a] += 1;
                    inDegree[b] -= 1;
                } else {
                    ArrayList<Integer> aTemp = graph.get(b);
                    aTemp.remove((Integer) a);
                    graph.put(b, aTemp);
                    ArrayList<Integer> bTemp = graph.get(a);
                    bTemp.add(b);
                    graph.put(a, bTemp);

                    inDegree[a] -= 1;
                    inDegree[b] += 1;
                }
            }

            Deque<Integer> deque = new ArrayDeque<>();
            for (int j = 1; j < n + 1; j++) {
                if (inDegree[j] == 0) {
                    deque.offer(j);
                }
            }

            List<Integer> result = new ArrayList<>();
            while (deque.size() != 0) {
                int num = deque.poll();
                result.add(num);

                for (Integer next : graph.get(num)) {
                    inDegree[next] -= 1;

                    if (inDegree[next] == 0) {
                        deque.offer(next);
                    }
                }
            }

            if (result.size() == n) {
                StringBuilder sb = new StringBuilder();
                for (Integer integer : result) {
                    sb.append(integer).append(" ");
                }
                System.out.println(sb);
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}
