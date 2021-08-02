package com.company.baekjun.b16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n, l, r;
    static int[][] country;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Country {
        int x;
        int y;

        public Country(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        l = Integer.parseInt(row[1]);
        r = Integer.parseInt(row[2]);

        country = new int[n][n];
        for (int i = 0; i < n; i++) {
            row = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                country[i][j] = Integer.parseInt(row[j]);
            }
        }

        int date = 0;
        while (true) {
            boolean[][] visited = new boolean[n][n];
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        count += bfs(new Country(i, j), visited);
                    }
                }
            }

            if (count == 0) {
                break;
            }

            date += 1;
        }

        System.out.println(date);
    }

    private static int bfs(Country startCountry, boolean[][] visited) {
        int totalPeople = country[startCountry.x][startCountry.y];
        int connectCountryCount = 1;

        Queue<Country> q = new LinkedList<>();
        Queue<Country> connectQ = new LinkedList<>();

        connectQ.offer(startCountry);
        q.offer(startCountry);
        visited[startCountry.x][startCountry.y] = true;

        while (!q.isEmpty()) {
            Country nextCountry = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nextCountry.x + dx[i];
                int ny = nextCountry.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 | ny >= n) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                int abs = Math.abs(country[nextCountry.x][nextCountry.y] - country[nx][ny]);
                if (abs <= r && abs >= l) {
                    totalPeople += country[nx][ny];
                    connectCountryCount += 1;
                    connectQ.offer(new Country(nx, ny));

                    visited[nx][ny] = true;
                    q.offer(new Country(nx, ny));
                }
            }
        }

        if (connectQ.size() == 1) {
            return 0;
        }

        while (!connectQ.isEmpty()) {
            Country connectCountry = connectQ.poll();
            country[connectCountry.x][connectCountry.y] = totalPeople / connectCountryCount;
        }

        return connectCountryCount;
    }
}
