package com.company.baekjun.b15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Spot> chickenHouses = new ArrayList<>();
    private static List<Spot> homes = new ArrayList<>();
    private static int minDistance = Integer.MAX_VALUE;

    static class Spot {
        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                if (input[j].equals("1")) {
                    homes.add(new Spot(i, j));
                } else if (input[j].equals("2")) {
                    chickenHouses.add(new Spot(i, j));
                }
            }
        }

        boolean[] visited = new boolean[chickenHouses.size()];
        combination(visited, m, 0, new ArrayList<>());

        System.out.println(minDistance);
    }

    private static void combination(boolean[] visited, int m, int start, List<Spot> combiChicken) {
        if (m == 0) {
            int minSumHomeDis = 0;
            for (Spot home : homes) {
                int minHomeDis = Integer.MAX_VALUE;
                for (Spot chicken : combiChicken) {
                    int distance = Math.abs(home.x - chicken.x) + Math.abs(home.y - chicken.y);
                    minHomeDis = Math.min(minHomeDis, distance);
                }
                minSumHomeDis += minHomeDis;
            }

            minDistance = Math.min(minDistance, minSumHomeDis);
            return;
        }

        for (int i = start; i < chickenHouses.size(); i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            combiChicken.add(chickenHouses.get(i));
            combination(visited, m - 1, i + 1, combiChicken);
            combiChicken.remove(combiChicken.size() - 1);
            visited[i] = false;
        }
    }
}
