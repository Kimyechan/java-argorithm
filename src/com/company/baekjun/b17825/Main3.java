package com.company.baekjun.b17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {
    static Node startNode = new Node(0);
    static int[] dices = new int[10];
    static int[] orders = new int[10];
    static int maxScore = Integer.MIN_VALUE;

    static class Node {
        int score;
        Node nextNode;
        Node fastPath;
        boolean isUsed;
        boolean isFinish;

        public Node(int score) {
            this.score = score;
        }

        public Node addNode(Node nextNode) {
            this.nextNode = nextNode;
            return nextNode;
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
            dices[i] = Integer.parseInt(input[i]);
        }

        init();
        dfs(0);

        System.out.println(maxScore);
    }

    private static void dfs(int depth) {
        if (depth >= 10) {
            Node[] horses = new Node[4];
            for (int i = 0; i < 4; i++) {
                horses[i] = startNode;
            }

            int score = 0;
            for (int i = 0; i < 10; i++) {
                Node node = horses[orders[i]];
                node.isUsed = false;

                if (node.fastPath == null) {
                    node = node.nextNode;
                } else {
                    node = node.fastPath;
                }

                int move = dices[i];
                for (int j = 0; j < move - 1; j++) {
                    node = node.nextNode;
                }

                if (node.isUsed && !node.isFinish) {
                    score = 0;
                    break;
                } else {
                    score += node.score;
                    node.isUsed = true;
                    horses[orders[i]] = node;
                }
            }

            for (int i = 0; i < 4; i++) {
                horses[i].isUsed = false;
            }

            maxScore = Math.max(maxScore, score);
            return;
        }

        for (int i = 0; i < 4; i++) {
            orders[depth] = i;
            dfs(depth + 1);
        }
    }

    private static void init() {
        Node node = startNode;
        for (int i = 2; i <= 40; i += 2) {
            node = node.addNode(new Node(i));
        }

        Node endNode = new Node(0);
        endNode.isFinish = true;
        endNode.nextNode = endNode;
        node.addNode(endNode);

        Node node25 = new Node(25);

        Node node10 = startNode.findNode(10);
        node10.fastPath = new Node(13);
        node10 = node10.fastPath;
        node10 = node10.addNode(new Node(16));
        node10 = node10.addNode(new Node(19));
        node10.addNode(node25);

        Node node20 = startNode.findNode(20);
        node20.fastPath = new Node(22);
        node20 = node20.fastPath;
        node20 = node20.addNode(new Node(24));
        node20.addNode(node25);

        Node node30 = startNode.findNode(30);
        node30.fastPath = new Node(28);
        node30 = node30.fastPath;
        node30 = node30.addNode(new Node(27));
        node30 = node30.addNode(new Node(26));
        node30.addNode(node25);

        node25 = node25.addNode(new Node(30));
        node25 = node25.addNode(new Node(35));
        node25.addNode(node);
    }
}