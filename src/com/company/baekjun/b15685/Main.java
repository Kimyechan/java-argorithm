package com.company.baekjun.b15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<DragonCurve> dragonCurves = new ArrayList<>();
    static int[][] board = new int[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class DragonCurve {
        int x;
        int y;
        int d;
        int g;

        public DragonCurve(int x, int y, int d, int g) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.g = g;
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

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            int x = Integer.parseInt(row[0]);
            int y = Integer.parseInt(row[1]);
            int d = Integer.parseInt(row[2]);
            int g = Integer.parseInt(row[3]);

            dragonCurves.add(new DragonCurve(x, y, d, g));
        }

        List<Node> totalDragonCurveNodes = new ArrayList<>();
        for (DragonCurve dragonCurve : dragonCurves) {
            List<Node> dragonCurveNodes = makeDragonCurve(dragonCurve);
            totalDragonCurveNodes.addAll(dragonCurveNodes);
        }

        for (Node dragonCurveNode : totalDragonCurveNodes) {
            board[dragonCurveNode.x][dragonCurveNode.y] = 1;
        }

        int squareCount = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int count = 0;

                for (int k = i; k < i + 2; k++) {
                    for (int l = j; l < j + 2; l++) {
                        if (board[k][l] == 1) {
                            count += 1;
                        }
                    }
                }

                if (count == 4) {
                    squareCount += 1;
                }
            }
        }

        System.out.println(squareCount);
    }

    private static List<Node> makeDragonCurve(DragonCurve dragonCurve) {
        List<Node> dragonCurveNodes = new ArrayList<>();
        dragonCurveNodes.add(new Node(dragonCurve.x, dragonCurve.y));

        int nx = dragonCurve.x + dx[dragonCurve.d];
        int ny = dragonCurve.y + dy[dragonCurve.d];

        if (nx < 0 || nx > 100 || ny < 0 || ny > 100) {
            return dragonCurveNodes;
        }

        dragonCurveNodes.add(new Node(nx, ny));

        markDragonCurve(dragonCurveNodes, new Node(nx, ny), dragonCurve.g);

        return dragonCurveNodes;
    }

    private static void markDragonCurve(List<Node> dragonCurveNodes, Node centerNode, int age) {
        if (age == 0) {
            return;
        }

        List<Node> dragonCurveNewNodes = new ArrayList<>();
        int startSize = dragonCurveNodes.size();
        for (int i = startSize - 2; i >= 0; i--) {
            Node node = dragonCurveNodes.get(i);

            int y = node.x - centerNode.x + centerNode.y;
            int x = (node.y - centerNode.y) * -1 + centerNode.x;

            if (x < 0 || x > 100 || y < 0 || y > 100) {
                continue;
            }

            dragonCurveNewNodes.add(new Node(x, y));
        }
        dragonCurveNodes.addAll(dragonCurveNewNodes);

        Node nextCenterNode = dragonCurveNewNodes.get(dragonCurveNewNodes.size() - 1);
        markDragonCurve(dragonCurveNodes, nextCenterNode, age - 1);
    }
}