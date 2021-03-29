package com.company.topologySort.줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<ArrayList<Integer>> order = new ArrayList<>();
        int[] inDegree = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            order.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            order.get(a).add(b);
            inDegree[b] += 1;
        }

        PriorityQueue<Integer> line = new PriorityQueue<>();

        for (int i = 1; i < n + 1; i++) {
            if (inDegree[i] == 0) {
                line.offer(i);
            }
        }

        while (!line.isEmpty()) {
            int student = line.poll();
            System.out.print(student + " ");

            for (Integer integer : order.get(student)) {
                inDegree[integer] -= 1;
                if (inDegree[integer] == 0){
                    line.offer(integer);
                }
            }
        }
    }
}
