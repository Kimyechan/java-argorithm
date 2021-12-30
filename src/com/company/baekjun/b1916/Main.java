package com.company.baekjun.b1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class City implements Comparable<City> {
        int number;
        int distance;

        public City(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }

        @Override
        public int compareTo(City o) {
            return this.distance - o.distance;
        }
    }

    static Map<Integer, List<City>> routeMap = new HashMap<>();
    static Queue<City> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        for (int i = 1; i < n + 1; i++) {
            routeMap.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] path = br.readLine().split(" ");

            int from = Integer.parseInt(path[0]);
            int to = Integer.parseInt(path[1]);
            int weight = Integer.parseInt(path[2]);

            routeMap.get(from).add(new City(to, weight));
        }

        String[] resultPath = br.readLine().split(" ");
        int start = Integer.parseInt(resultPath[0]);
        int end = Integer.parseInt(resultPath[1]);

        int[] distances = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        pq.offer(new City(start, 0));

        while (!pq.isEmpty()) {
            City city = pq.poll();

            if (distances[city.number] < city.distance) {
                continue;
            }

            for (City nextCity : routeMap.get(city.number)) {
                int dis = city.distance + nextCity.distance;
                if (distances[nextCity.number] > dis) {
                    distances[nextCity.number] = dis;
                    pq.offer(new City(nextCity.number, dis));
                }
            }
        }

        System.out.println(distances[end]);
    }
}
