package com.company.programmers.kakao2019.길찾기게임;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    static  List<Integer> preOrderList = new ArrayList<>();
    static  List<Integer> postOrderList = new ArrayList<>();

    static class Node {
        int num;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            nodeList.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        nodeList.sort(Comparator.comparingInt(n -> n.x));

        Node root = null;
        int rootIdx = 0;
        int maxY = 0;
        for (int i = 0; i < nodeList.size(); i++) {
            if (maxY <= nodeList.get(i).y) {
                maxY = nodeList.get(i).y;
                root = nodeList.get(i);
                rootIdx = i;
            }
        }

        makeTree(root, rootIdx, nodeList);
        preOrder(root);
        postOrder(root);

        answer = new int[2][nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            answer[0][i] = preOrderList.get(i);
        }

        for (int i = 0; i < nodeList.size(); i++) {
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
        postOrderList.add(root.num);
    }

    private void preOrder(Node root) {
        if (root == null) {
            return;
        }

        preOrderList.add(root.num);
        preOrder(root.left);
        preOrder(root.right);
    }

    private void makeTree(Node root, int rootIdx, List<Node> nodeList) {
        Node left = null;
        int leftMaxY = 0;
        int leftRootIdx = 0;
        List<Node> leftNodeList = nodeList.subList(0, rootIdx);
        for (int i = 0; i < leftNodeList.size(); i++) {
            if (leftMaxY <= leftNodeList.get(i).y) {
                leftMaxY = leftNodeList.get(i).y;
                left = leftNodeList.get(i);
                leftRootIdx = i;
            }
        }

        Node right = null;
        int rightMaxY = 0;
        int rightRootIdx = 0;
        List<Node> rightNodeList = nodeList.subList(rootIdx + 1, nodeList.size());
        for (int i = 0; i < rightNodeList.size(); i++) {
            if (rightMaxY <= rightNodeList.get(i).y) {
                rightMaxY = rightNodeList.get(i).y;
                right = rightNodeList.get(i);
                rightRootIdx = i;
            }
        }

        root.left = left;
        root.right = right;

        if (left != null) {
            makeTree(left, leftRootIdx, leftNodeList);
        }

        if (right != null) {
            makeTree(right, rightRootIdx, rightNodeList);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[][]{{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}});
    }
}