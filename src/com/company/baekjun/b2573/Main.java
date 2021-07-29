package com.company.baekjun.b2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

// wrong
public class Main {
    static int n;
    static int m;
    static int[][] sea;
    static int[][] waterCounts;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static LinkedList<Ice> q = new LinkedList<>();
    static int answer;

    static class Ice {
        int x;
        int y;
        int height;

        public Ice(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        m = Integer.parseInt(row[1]);

        sea = new int[n][m];
        for (int i = 0; i < n; i++) {
            row = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                sea[i][j] = Integer.parseInt(row[j]);
            }
        }

        waterCounts = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (sea[i][j] != 0) {
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = i + dy[k];
                        if (sea[nx][ny] == 0) {
                            count += 1;
                        }
                    }
                    q.offer(new Ice(i, j, sea[i][j]));
                    waterCounts[i][j] = count;
                }
            }
        }

        answer = passTime();

        System.out.println(answer);
    }

    private static int passTime() {
        int time = 0;
        while (!q.isEmpty()) {
            int iceCount = q.size();
            for (int i = 0; i < iceCount; i++) {
                if (q.isEmpty()) {
                    return 0;
                }

                Ice ice = q.poll();
                int meetWaterCount = waterCounts[ice.x][ice.y];
                int height = ice.height - meetWaterCount;
                if (height > 0) {
                    sea[ice.x][ice.y] = height;
                    q.offer(new Ice(ice.x, ice.y, height));
                } else {
                    sea[ice.x][ice.y] = 0;
                    for (int j = 0; j < 4; j++) {
                        if (waterCounts[ice.x + dx[j]][ice.y + dy[j]] != 0) {
                            waterCounts[ice.x + dx[j]][ice.y + dy[j]] += 1;
                        }
                    }
                }
            }

            time += 1;
            if (q.isEmpty()) {
                return 0;
            }

            boolean[][] visited = new boolean[n][m];
            Deque<Node> bfsQ = new ArrayDeque<>();

            int pieceCount = 0;
            for (int num = 0; num < q.size(); num++) {
                Ice ice = q.get(num);
                int i = ice.x;
                int j = ice.y;

                if (sea[i][j] == 0) {
                    continue;
                }

                if (visited[i][j]) {
                    continue;
                }

                pieceCount += 1;
                if (pieceCount >= 2) {
                    return time;
                }

                bfsQ.offerLast(new Node(i, j));
                visited[i][j] = true;

                while (!bfsQ.isEmpty()) {
                    Node nextNode = bfsQ.pollFirst();

                    for (int k = 0; k < 4; k++) {
                        int nx = nextNode.x + dx[k];
                        int ny = nextNode.y + dy[k];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                            continue;
                        }

                        if (visited[nx][ny]) {
                            continue;
                        }

                        if (sea[nx][ny] == 0) {
                            continue;
                        }

                        visited[nx][ny] = true;
                        bfsQ.offerLast(new Node(nx, ny));
                    }
                }
            }
        }

        return 0;
    }
}