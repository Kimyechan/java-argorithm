package com.company.baekjun.b17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main4 {
    static Node start = new Node(0);
    static int[] moves = new int[10];
    static int maxScore = 0;

    static class Node {
        int score;
        Node nextNode;
        Node fastPath;
        boolean isUsed;
        boolean isFinish;

        public Node(int score) {
            this.score = score;
        }

        public Node addNode(int score) {
            Node node = new Node(score);
            this.nextNode = node;

            return node;
        }

        public Node findNode(int score) {
            Node node = this;
            while (node.score != score) {
                node = node.nextNode;
            }

            return node;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < 10; i++) {
            moves[i] = Integer.parseInt(input[i]);
        }

        initBoard();

        dfs(new ArrayList<>());

        System.out.println(maxScore);
    }

    private static void dfs(List<Integer> orders) {
        if (orders.size() == 10) {
            Node[] node = new Node[4];
            for (int i = 0; i < 4; i++) {
                node[i] = start;
            }
            int totalScore = 0;

            for (int i = 0; i < 10; i++) {
                int order = orders.get(i);
                int move = moves[i];

                node[order].isUsed = false;

                if (node[order].fastPath == null) {
                    for (int j = 0; j < move; j++) {
                        node[order] = node[order].nextNode;
                    }
                } else {
                    node[order] = node[order].fastPath;
                    for (int j = 0; j < move - 1; j++) {
                        node[order] = node[order].nextNode;
                    }
                }
                if (node[order].isUsed && !node[order].isFinish) {
                    totalScore = 0;
                    break;
                }
                totalScore += node[order].score;
                node[order].isUsed = true;
            }

            for (int i = 0; i < 4; i++) {
                node[i].isUsed = false;
            }

            maxScore = Math.max(maxScore, totalScore);
            return;
        }

        for (int i = 0; i < 4; i++) {
            orders.add(i);
            dfs(orders);
            orders.remove(orders.size() - 1);
        }
    }

    private static void initBoard() {
        Node node = start.addNode(2);

        for (int i = 4; i < 40; i += 2) {
            node = node.addNode(i);
        }
        Node node40 = node.addNode(40);

        Node endNode = new Node(0);
        endNode.isFinish = true;

        node40.nextNode = endNode;
        endNode.nextNode = endNode;

        Node node25 = new Node(25);

        Node node10 = start.findNode(10);
        node10.fastPath = new Node(13);
        node10 = node10.fastPath.addNode(16);
        node10 = node10.addNode(19);
        node10.nextNode = node25;

        Node node20 = start.findNode(20);
        node20.fastPath = new Node(22);
        node20 = node20.fastPath.addNode(24);
        node20.nextNode = node25;

        Node node30 = start.findNode(30);
        node30.fastPath = new Node(28);
        node30 = node30.fastPath.addNode(27);
        node30 = node30.addNode(26);
        node30.nextNode = node25;

        node25 = node25.addNode(30);
        node25 = node25.addNode(35);
        node25.nextNode = node40;
    }
}