package com.company.programmers.kakao2019.길찾기게임;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    static List<Node> nodeList = new ArrayList<>();
    static List<Integer> preOrderList = new ArrayList<>();
    static List<Integer> postOrderList = new ArrayList<>();

    static class Node {
        int number;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        for (int i = 0; i < nodeinfo.length; i++) {
            nodeList.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        nodeList.sort((n1, n2) -> n2.y - n1.y);

        Node root = nodeList.get(0);
        for (int i = 1; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            makeTree(root, node);
        }

        preOrder(root);
        postOrder(root);

        answer = new int[2][nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            answer[0][i] = preOrderList.get(i);
            answer[1][i] = postOrderList.get(i);
        }

        return answer;
    }

    private void postOrder(Node root) {
        if (root == null) {
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        postOrderList.add(root.number);
    }

    private void preOrder(Node root) {
        if (root == null) {
            return;
        }

        preOrderList.add(root.number);
        preOrder(root.left);
        preOrder(root.right);
    }

    private void makeTree(Node root, Node node) {
        if (root.x < node.x) {
            if (root.right == null) {
                root.right = node;
            } else {
                makeTree(root.right, node);
            }
        } else {
            if (root.left == null) {
                root.left = node;
            } else {
                makeTree(root.left, node);
            }
        }
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        solution.solution(new int[][]{{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}});
    }
}