package com.company.topologySort.ACMCraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Building implements Comparable{
        Integer time;
        Integer number;

        public Building(Integer time, Integer number) {
            this.time = time;
            this.number = number;
        }

        @Override
        public int compareTo(Object o) {
            Building b = (Building) o;
            return this.time>b.time ? 1 : -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            List<Integer> times = new ArrayList<>();
            times.add(0);
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                times.add(Integer.parseInt(st.nextToken()));
            }

            List<ArrayList<Integer>> orders = new ArrayList<>();
            for (int j = 0; j < n + 1; j++) {
                orders.add(new ArrayList<>());
            }
            int[] inDegree = new int[n + 1];

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                orders.get(a).add(b);
                inDegree[b] += 1;
            }

            PriorityQueue<Building> queue = new PriorityQueue<>();
            for (int j = 1; j < n + 1; j++) {
                if (inDegree[j] == 0) {
                    queue.offer(new Building(times.get(j), j));
                }
            }

            int lastBuilding = Integer.parseInt(br.readLine());
            int maxTime = 0;
            while (queue.size() != 0) {
                Building building = queue.poll();

                if (lastBuilding == building.number) {
                    maxTime = building.time;
                }

                for (Integer nextNumber : orders.get(building.number)) {
                    inDegree[nextNumber] -= 1;

                    if (inDegree[nextNumber] == 0) {
                        queue.offer(new Building(building.time + times.get(nextNumber), nextNumber));
                    }
                }
            }

            System.out.println(maxTime);
        }
    }
}
