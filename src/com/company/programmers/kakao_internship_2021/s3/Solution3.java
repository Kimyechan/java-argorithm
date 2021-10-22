package com.company.programmers.kakao_internship_2021.s3;

import java.util.Stack;

public class Solution3 {

    class Node {
        int number;
        Node prev;
        Node next;
        boolean isRemoved;

        public Node(int number, boolean isRemoved) {
            this.number = number;
            this.isRemoved = isRemoved;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, false);
        }

        for (int i = 1; i < n; i++) {
            nodes[i - 1].next = nodes[i];
            nodes[i].prev = nodes[i - 1];
        }

        Node cur = nodes[k];
        Stack<Node> stack = new Stack<>();
        for (String c : cmd) {
            String[] input = c.split(" ");
            if (input[0].equals("D")) {
                int move = Integer.parseInt(input[1]);
                for (int i = 0; i < move; i++) {
                    cur = cur.next;
                }
            } else if (input[0].equals("U")) {
                int move = Integer.parseInt(input[1]);
                for (int i = 0; i < move; i++) {
                    cur = cur.prev;
                }
            } else if (input[0].equals("C")) {
                Node prevNode = cur.prev;
                Node nextNode = cur.next;

                cur.isRemoved = true;
                stack.push(cur);

                if (prevNode == null) {
                    nextNode.prev = null;
                    cur = nextNode;
                } else if (nextNode == null) {
                    prevNode.next = null;
                    cur = prevNode;
                } else {
                    prevNode.next = nextNode;
                    nextNode.prev = prevNode;
                    cur = nextNode;
                }
            } else if (input[0].equals("Z")) {
                Node nodeBack = stack.pop();
                nodeBack.isRemoved = false;

                Node prevNode = nodeBack.prev;
                Node nextNode = nodeBack.next;

                if (prevNode != null) {
                    prevNode.next = nodeBack;
                }

                if (nextNode != null) {
                    nextNode.prev = nodeBack;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (nodes[i].isRemoved) {
                answer.append("X");
            } else {
                answer.append("O");
            }
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        System.out.println(solution.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
//        System.out.println(solution.solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
    }
}
