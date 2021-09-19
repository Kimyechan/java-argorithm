package com.company.baekjun.b17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static Node start = new Node(0);
    static int[] dices = new int[10];
    static int[] orders = new int[10];
    static int maxScore = 0;
    static Node[] horse = new Node[4];

    static class Node {
        int score;
        boolean isNotEmpty;
        boolean isFinish;
        Node fastPath;
        Node nextNode;

        public Node(int score) {
            this.score = score;
        }

        public Node addNode(int score) {
            this.nextNode = new Node(score);
            return this.nextNode;
        }

        public Node findNode(int target) {
            Node node = start;
            while (true) {
                Node temp = node.nextNode;
                if (temp.score == target) {
                    return temp;
                }
                node = node.nextNode;
            }
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
            maxScore = Math.max(maxScore, startGame());
            return;
        }

        for (int i = 0; i < 4; i++) {
            orders[depth] = i;
            dfs(depth + 1);
        }
    }

    private static int startGame() {
        for (int i = 0; i < 4; i++) {
            horse[i] = start;
        }

        int score = 0;
        for (int i = 0; i < 10; i++) {
            Node node = horse[orders[i]];
            node.isNotEmpty = false;

            for (int j = 0; j < dices[i]; j++) {
                if (j == 0 && node.fastPath != null) {
                    node = node.fastPath;
                } else {
                    node = node.nextNode;
                }
            }

            if (node.isNotEmpty && !node.isFinish) {
                score = 0;
                break;
            } else {
                score += node.score;
                node.isNotEmpty = true;
                horse[orders[i]] = node;
            }
        }

        for (int i = 0; i < 4; i++) {
            horse[i].isNotEmpty = false;
        }

        return score;
    }

    private static void init() {
        Node node = start;
        for (int i = 2; i <= 40; i += 2) {
            node = node.addNode(i);
        }

        Node end = node.addNode(0);
        end.isFinish = true;
        end.nextNode = end;

        Node node25 = new Node(25);

        Node node10 = start.findNode(10);
        node10.fastPath = new Node(13);
        node10 = node10.fastPath;
        node10 = node10.addNode(16);
        node10 = node10.addNode(19);
        node10.nextNode = node25;

        Node node20 = start.findNode(20);
        node20.fastPath = new Node(22);
        node20 = node20.fastPath;
        node20 = node20.addNode(24);
        node20.nextNode = node25;

        Node node30 = start.findNode(30);
        node30.fastPath = new Node(28);
        node30 = node30.fastPath;
        node30 = node30.addNode(27);
        node30 = node30.addNode(26);
        node30.nextNode = node25;

        node25 = node25.addNode(30);
        node25 = node25.addNode(35);
        node25.nextNode = node;
    }
}